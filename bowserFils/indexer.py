"""

indexer.py


Responsable de l'indexation des fichiers.

Auteur : ramdane

"""

from __future__ import annotations

import logging
import threading
import time

from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path

from config import (
    LOG_FILE,
    LOG_LEVEL,
    LOG_FORMAT,
    MAX_THREADS
)

from database import Database
from scanner import FileScanner
from utils import (
    get_file_information,
    filter_indexable_files
)




logging.basicConfig(

    filename=LOG_FILE,

    level=getattr(logging, LOG_LEVEL),

    format=LOG_FORMAT

)




class FileIndexer:
    """
    Moteur principal d'indexation.
    """

    def __init__(self, directory: str):

        self.directory = Path(directory).resolve()

        self.database = Database()

        self.scanner = FileScanner(str(self.directory))

        

        self.total_files = 0

        self.indexed_files = 0

        self.updated_files = 0

        self.deleted_files = 0

        self.failed_files = 0

        self.skipped_files = 0

        

        self.start_time = 0.0

        self.end_time = 0.0

        

        self.current_file = 0

        

        self.counter_lock = threading.Lock()

        self.database_lock = threading.Lock()

        logging.info(
            "Initialisation du moteur d'indexation."
        )

    

    def reset_statistics(self) -> None:
        """
        Réinitialise les statistiques.
        """

        self.total_files = 0

        self.indexed_files = 0

        self.updated_files = 0

        self.deleted_files = 0

        self.failed_files = 0

        self.skipped_files = 0

        self.current_file = 0

    

    def run(self) -> None:
        """
        Point d'entrée principal.
        """

        self.reset_statistics()

        self.start_time = time.perf_counter()

        logging.info("Début de l'indexation.")

        print()

        print("=" * 70)
        print("FILE SEARCH ENGINE")
        print("=" * 70)

        print(f"Dossier : {self.directory}")

        print()

        try:

            self.build_index()

        except KeyboardInterrupt:

            print("\nIndexation interrompue.")

            logging.warning(
                "Interruption utilisateur."
            )

        except Exception as error:

            self.failed_files += 1

            logging.exception(error)

            print(f"\nErreur : {error}")

        finally:

            self.end_time = time.perf_counter()

            self.print_statistics()

            logging.info(
                "Fin de l'indexation."
            )

    

    def prepare_files(self) -> list[Path]:
        """
        Recherche les fichiers
        puis filtre ceux pouvant être indexés.
        """

        files = self.scanner.scan()

        files = filter_indexable_files(files)

        self.total_files = len(files)

        logging.info(

            "%s fichiers trouvés.",

            self.total_files

        )

        return files

    

    def update_progress(self) -> None:
        """
        Met à jour la progression.
        """

        with self.counter_lock:

            self.current_file += 1

            print(

                f"\rProgression : "

                f"{self.current_file}"

                f"/"

                f"{self.total_files}",

                end="",

                flush=True

            )
                

    def build_index(self) -> None:
        """
        Lance l'indexation complète.
        """

        files = self.prepare_files()

        if not files:

            print("Aucun fichier à indexer.")

            return

        print(f"{self.total_files} fichier(s) à indexer.\n")

        self.process_files(files)

        print("\n")

    

    def process_files(
        self,
        files: list[Path]
    ) -> None:
        """
        Traite tous les fichiers
        avec plusieurs threads.
        """

        with ThreadPoolExecutor(
            max_workers=MAX_THREADS
        ) as executor:

            futures = {

                executor.submit(
                    self.index_single_file,
                    file
                ): file

                for file in files

            }

            for future in as_completed(futures):

                try:

                    future.result()

                except Exception as error:

                    with self.counter_lock:

                        self.failed_files += 1

                    logging.exception(error)

    

    def index_single_file(
        self,
        file_path: Path
    ) -> None:
        """
        Indexe un fichier.
        """

        try:

            info = get_file_information(file_path)

            if not info:

                with self.counter_lock:

                    self.skipped_files += 1

                self.update_progress()

                return

            self.save_to_database(info)

            with self.counter_lock:

                self.indexed_files += 1

            self.update_progress()

            logging.info(

                "Indexé : %s",

                info["path"]

            )

        except Exception as error:

            with self.counter_lock:

                self.failed_files += 1

            logging.exception(error)

    

    def save_to_database(
        self,
        info: dict
    ) -> None:
        """
        Enregistre un fichier
        dans la base SQLite.
        """

        with self.database_lock:

            self.database.insert_or_update(

                path=info["path"],

                name=info["name"],

                extension=info["extension"],

                size=info["size"],

                modified=info["modified"],

                file_hash=info["hash"],

                content=info["content"]

            )

    

    def index_file(
        self,
        file_path: str
    ) -> None:
        """
        Indexe un seul fichier.
        """

        path = Path(file_path)

        if not path.exists():

            print("Fichier introuvable.")

            return

        self.index_single_file(path)

    

    def index_files(
        self,
        files: list[Path]
    ) -> None:
        """
        Indexe une liste de fichiers.
        """

        self.total_files = len(files)

        self.process_files(files)

    

    def needs_update(
        self,
        file_path: Path
    ) -> bool:
        """
        Retourne True si le fichier est nouveau
        ou s'il a été modifié.
        """

        record = self.database.get_file(
            str(file_path.resolve())
        )

        # Nouveau fichier
        if record is None:
            return True

        current_modified = file_path.stat().st_mtime

        return current_modified != record["modified"]


    

    def update_index(self) -> None:
        """
        Met à jour uniquement les fichiers
        nouveaux ou modifiés.
        """

        print("\nMise à jour de l'index...\n")

        self.reset_statistics()

        self.start_time = time.perf_counter()

        files = self.prepare_files()

        if not files:

            print("Aucun fichier trouvé.")

            return

        self.process_updates(files)

        self.remove_deleted_files(files)

        self.end_time = time.perf_counter()

        self.print_statistics()


    

    def process_updates(
        self,
        files: list[Path]
    ) -> None:

        with ThreadPoolExecutor(
            max_workers=MAX_THREADS
        ) as executor:

            futures = [

                executor.submit(
                    self.update_single_file,
                    file
                )

                for file in files

            ]

            for future in as_completed(futures):

                try:

                    future.result()

                except Exception as error:

                    with self.counter_lock:

                        self.failed_files += 1

                    logging.exception(error)


    

    def update_single_file(
        self,
        file_path: Path
    ) -> None:

        # Rien n'a changé
        if not self.needs_update(file_path):

            with self.counter_lock:

                self.skipped_files += 1

            self.update_progress()

            return

        try:

            info = get_file_information(file_path)

            self.save_to_database(info)

            with self.counter_lock:

                self.updated_files += 1

            self.update_progress()

            logging.info(

                "Mis à jour : %s",

                info["path"]

            )

        except Exception as error:

            with self.counter_lock:

                self.failed_files += 1

            logging.exception(error)


    

    def remove_deleted_files(
        self,
        scanned_files: list[Path]
    ) -> None:

        scanned_paths = {

            str(file.resolve())

            for file in scanned_files

        }

        indexed_paths = self.database.get_all_paths()

        deleted_files = indexed_paths - scanned_paths

        if not deleted_files:

            return

        print(

            f"\nSuppression de "

            f"{len(deleted_files)} fichier(s)..."

        )

        for path in deleted_files:

            self.database.delete(path)

            with self.counter_lock:

                self.deleted_files += 1

            logging.info(

                "Supprimé : %s",

                path

            )


    

    def rebuild_index(self) -> None:
        """
        Supprime complètement l'ancien index
        puis lance une nouvelle indexation.
        """

        print("\nReconstruction complète...\n")

        logging.info(

            "Reconstruction de l'index."

        )

        self.database.clear()

        self.run()


    

    def clear_index(self) -> None:
        """
        Vide complètement la base.
        """

        self.database.clear()

        print("\nIndex supprimé.\n")

        logging.info(

            "Index supprimé."

        )
           

    def execution_time(self) -> float:
        """
        Retourne la durée totale de l'indexation.
        """

        return self.end_time - self.start_time


    

    def print_statistics(self) -> None:
        """
        Affiche les statistiques finales.
        """

        duration = self.execution_time()

        print()

        print("=" * 70)
        print("INDEXATION TERMINÉE")
        print("=" * 70)

        print(f"Fichiers analysés        : {self.total_files}")
        print(f"Fichiers indexés         : {self.indexed_files}")
        print(f"Fichiers mis à jour      : {self.updated_files}")
        print(f"Fichiers ignorés         : {self.skipped_files}")
        print(f"Fichiers supprimés       : {self.deleted_files}")
        print(f"Erreurs                  : {self.failed_files}")
        print(f"Durée                    : {duration:.2f} seconde(s)")
        print(f"Nombre en base           : {self.database.count()}")

        print("=" * 70)

        logging.info(

            "Analyse=%s | "
            "Indexés=%s | "
            "Mis à jour=%s | "
            "Ignorés=%s | "
            "Supprimés=%s | "
            "Erreurs=%s | "
            "Durée=%.2fs",

            self.total_files,
            self.indexed_files,
            self.updated_files,
            self.skipped_files,
            self.deleted_files,
            self.failed_files,
            duration

        )


    

    def show_database_information(self) -> None:
        """
        Affiche les statistiques de la base.
        """

        stats = self.database.get_statistics()

        total_files = stats.get("total_files", 0)

        total_size = stats.get("total_size") or 0

        average_size = stats.get("average_size") or 0

        minimum_size = stats.get("minimum_size") or 0

        maximum_size = stats.get("maximum_size") or 0

        print()

        print("=" * 70)
        print("BASE SQLITE")
        print("=" * 70)

        print(f"Nombre de fichiers : {total_files}")
        print(f"Taille totale      : {total_size} octets")
        print(f"Taille moyenne     : {average_size:.2f} octets")
        print(f"Plus petit fichier : {minimum_size} octets")
        print(f"Plus gros fichier  : {maximum_size} octets")

        print("=" * 70)


    

    def show_extensions(self) -> None:
        """
        Affiche les statistiques
        des extensions.
        """

        extensions = self.database.get_extensions_statistics()

        print()

        print("=" * 70)
        print("EXTENSIONS")
        print("=" * 70)

        if not extensions:

            print("Aucune donnée.")

            print("=" * 70)

            return

        for row in extensions:

            extension = row["extension"]

            if not extension:

                extension = "(sans extension)"

            print(

                f"{extension:<20}"

                f"{row['total']} fichier(s)"

            )

        print("=" * 70)


    

    def summary(self) -> dict:
        """
        Retourne les statistiques
        sous forme de dictionnaire.
        """

        return {

            "total_files": self.total_files,

            "indexed_files": self.indexed_files,

            "updated_files": self.updated_files,

            "deleted_files": self.deleted_files,

            "failed_files": self.failed_files,

            "skipped_files": self.skipped_files,

            "duration": self.execution_time()

        }


    

    def close(self) -> None:
        """
        Fermeture propre.
        """

        try:

            self.database.close()

        except Exception:

            pass

        logging.info(

            "Moteur d'indexation fermé."

        )


    

    def __del__(self):

        self.close()
