"""
=============================================================
cli.py

Projet : File Search Engine

Interface en ligne de commande.

Auteur : remdane
Version : 2.0
=============================================================
"""

from __future__ import annotations

import argparse

from config import (
    PROGRAM_NAME,
    VERSION,
    DESCRIPTION
)
from indexer import FileIndexer
from search import FileSearch


class CommandLineInterface:
    """
    Gestionnaire de l'interface
    en ligne de commande.
    """

    def __init__(self) -> None:

        self.parser = argparse.ArgumentParser(

            prog=PROGRAM_NAME,

            description=DESCRIPTION,

            formatter_class=argparse.ArgumentDefaultsHelpFormatter

        )

        self.create_arguments()

    

    def create_arguments(self) -> None:

        

        self.parser.add_argument(

            "--version",

            action="version",

            version=f"%(prog)s {VERSION}"

        )

        

        indexing = self.parser.add_argument_group(

            "Indexation"

        )

        indexing.add_argument(

            "--index",

            metavar="DIRECTORY",

            help="Indexer un dossier."

        )

        indexing.add_argument(

            "--update",

            metavar="DIRECTORY",

            help="Mettre à jour un dossier déjà indexé."

        )

        indexing.add_argument(

            "--rebuild",

            metavar="DIRECTORY",

            help="Reconstruire complètement l'index."

        )

        

        search = self.parser.add_argument_group(

            "Recherche"

        )

        search.add_argument(

            "--name",

            metavar="TEXT",

            help="Recherche par nom."

        )

        search.add_argument(

            "--content",

            metavar="TEXT",

            help="Recherche dans le contenu."

        )

        search.add_argument(

            "--extension",

            metavar="EXT",

            help="Recherche par extension."

        )

        search.add_argument(

            "--path",

            metavar="TEXT",

            help="Recherche dans le chemin."

        )

        search.add_argument(

            "--size",

            metavar="SIZE",

            type=int,

            help="Recherche les fichiers ayant une taille minimale."

        )

        search.add_argument(

            "--search",

            metavar="TEXT",

            help="Recherche globale."

        )

        

        information = self.parser.add_argument_group(

            "Informations"

        )

        information.add_argument(

            "--stats",

            action="store_true",

            help="Afficher les statistiques."

        )

        information.add_argument(

            "--extensions",

            action="store_true",

            help="Afficher les statistiques par extension."

        )

        information.add_argument(

            "--recent",

            metavar="N",

            nargs="?",

            const=10,

            type=int,

            help="Afficher les derniers fichiers."

        )

        information.add_argument(

            "--list",

            action="store_true",

            help="Afficher tous les fichiers."

        )

        information.add_argument(

            "--count",

            action="store_true",

            help="Afficher le nombre de fichiers."

        )

        

        database = self.parser.add_argument_group(

            "Base de données"

        )

        database.add_argument(

            "--clear",

            action="store_true",

            help="Supprimer complètement l'index."

        )

    

    def parse(self) -> argparse.Namespace:
        """
        Analyse les arguments.
        """

        return self.parser.parse_args()
        





def execute() -> None:
    """
    Exécute la commande demandée.
    """

    cli = CommandLineInterface()

    args = cli.parse()

    indexer = None

    search = None

    try:

       

        if args.index:

            indexer = FileIndexer(args.index)

            indexer.run()

            return

        if args.update:

            indexer = FileIndexer(args.update)

            indexer.update_index()

            return

        if args.rebuild:

            indexer = FileIndexer(args.rebuild)

            indexer.rebuild_index()

            return

        

        search = FileSearch()

        if args.name:

            search.search_by_name(args.name)

            return

        if args.content:

            search.search_by_content(args.content)

            return

        if args.extension:

            search.search_by_extension(args.extension)

            return

        if args.path:

            search.search_by_path(args.path)

            return

        if args.size is not None:

            search.search_by_size(args.size)

            return

        if args.search:

            search.search_all(args.search)

            return

        

        if args.stats:

            search.show_statistics()

            return

        if args.extensions:

            search.show_extensions_statistics()

            return

        if args.recent is not None:

            search.show_recent_files(args.recent)

            return

        if args.list:

            search.show_all_files()

            return

        if args.count:

            print()

            print(

                f"{search.count()} fichier(s) indexé(s)."

            )

            print()

            return

        

        if args.clear:

            search.clear_database()

            return

        

        cli.parser.print_help()

    except KeyboardInterrupt:

        print("\n\nInterruption utilisateur.")

    except Exception as error:

        print()

        print(f"Erreur : {error}")

        print()

    finally:

        if indexer is not None:

            indexer.close()

        if search is not None:

            search.close()
