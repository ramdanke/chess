"""

search.py



Gestion des recherches dans la base SQLite.

Auteur : ramdane

"""

from __future__ import annotations

from database import Database
from utils import human_size


class FileSearch:
    """
    Gestionnaire des recherches.
    """

    def __init__(self) -> None:

        self.database = Database()

    

    def display_results(self, results) -> None:
        """
        Affiche les résultats d'une recherche.
        """

        if not results:

            print("\nAucun résultat trouvé.\n")

            return

        print()

        print("=" * 100)

        print(f"{len(results)} résultat(s) trouvé(s)")

        print("=" * 100)

        for index, row in enumerate(results, start=1):

            print(f"\nRésultat {index}")

            print("-" * 100)

            print(f"Nom                  : {row['name']}")

            print(f"Chemin               : {row['path']}")

            print(f"Extension            : {row['extension']}")

            print(f"Taille               : {human_size(row['size'])}")

            print(f"Dernière modification: {row['modified']}")

        print()

    

    def search_by_name(
        self,
        keyword: str
    ):

        results = self.database.search_by_name(keyword)

        self.display_results(results)

        return results

   

    def search_by_content(
        self,
        keyword: str
    ):

        results = self.database.search_by_content(keyword)

        self.display_results(results)

        return results

    

    def search_by_extension(
        self,
        extension: str
    ):

        results = self.database.search_by_extension(
            extension
        )

        self.display_results(results)

        return results

   

    def search_by_path(
        self,
        keyword: str
    ):

        results = self.database.search_by_path(keyword)

        self.display_results(results)

        return results

    

    def search_by_size(
        self,
        minimum_size: int
    ):

        results = self.database.search_by_size(
            minimum_size
        )

        self.display_results(results)

        return results

    

    def search_all(
        self,
        keyword: str
    ):

        results = self.database.search_all(keyword)

        self.display_results(results)

        return results

    

    def show_statistics(self) -> None:

        stats = self.database.get_statistics()

        total_files = stats.get("total_files", 0)

        total_size = int(stats.get("total_size") or 0)

        average_size = int(stats.get("average_size") or 0)

        minimum_size = int(stats.get("minimum_size") or 0)

        maximum_size = int(stats.get("maximum_size") or 0)

        print()

        print("=" * 70)

        print("STATISTIQUES")

        print("=" * 70)

        print(f"Nombre de fichiers : {total_files}")

        print(
            f"Taille totale      : "
            f"{human_size(total_size)}"
        )

        print(
            f"Taille moyenne     : "
            f"{human_size(average_size)}"
        )

        print(
            f"Plus petit fichier : "
            f"{human_size(minimum_size)}"
        )

        print(
            f"Plus gros fichier  : "
            f"{human_size(maximum_size)}"
        )

        print("=" * 70)
            

    def show_extensions_statistics(self) -> None:
        """
        Affiche les statistiques
        des extensions.
        """

        results = self.database.get_extensions_statistics()

        print()

        print("=" * 70)

        print("EXTENSIONS")

        print("=" * 70)

        if not results:

            print("Aucune donnée.")

            print("=" * 70)

            return

        for row in results:

            extension = row["extension"]

            if not extension:

                extension = "(sans extension)"

            print(

                f"{extension:<20}"

                f"{row['total']} fichier(s)"

            )

        print("=" * 70)


    

    def show_recent_files(
        self,
        limit: int = 10
    ) -> None:
        """
        Affiche les derniers fichiers modifiés.
        """

        results = self.database.get_recent_files(limit)

        print()

        print("=" * 70)

        print("DERNIERS FICHIERS")

        print("=" * 70)

        if not results:

            print("Aucun fichier.")

            print("=" * 70)

            return

        for row in results:

            print(f"Nom      : {row['name']}")

            print(f"Chemin   : {row['path']}")

            print(f"Taille   : {human_size(row['size'])}")

            print()


  

    def get_file(
        self,
        path: str
    ):
        """
        Retourne un fichier
        à partir de son chemin.
        """

        result = self.database.get_file(path)

        if result is None:

            print("\nFichier introuvable.\n")

            return None

        print()

        print("=" * 70)

        print("FICHIER")

        print("=" * 70)

        print(f"Nom        : {result['name']}")

        print(f"Chemin     : {result['path']}")

        print(f"Extension  : {result['extension']}")

        print(f"Taille     : {human_size(result['size'])}")

        print("=" * 70)

        return result


   

    def count(self) -> int:

        return self.database.count()


    

    def show_all_files(self) -> None:
        """
        Affiche tous les fichiers.
        """

        results = self.database.get_all_files()

        self.display_results(results)


   

    def clear_database(self) -> None:
        """
        Vide complètement
        la base SQLite.
        """

        self.database.clear()

        print("\nBase de données vidée.\n")


    

    def summary(self) -> dict:
        """
        Retourne un résumé
        des statistiques.
        """

        stats = self.database.get_statistics()

        return {

            "files": stats.get("total_files", 0),

            "size": stats.get("total_size", 0)

        }


    

    def close(self) -> None:
        """
        Fermeture propre.
        """

        self.database.close()


    

    def run(self) -> None:

        print()

        print("=" * 70)

        print("FILE SEARCH ENGINE")

        print("=" * 70)

        print(

            f"{self.count()} fichier(s) actuellement indexé(s)."

        )

        print("=" * 70)
