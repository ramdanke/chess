"""

scanner.py


Parcours récursif des dossiers.

Auteur : ramdane

"""

from __future__ import annotations

from pathlib import Path
from typing import Generator

from config import IGNORED_DIRECTORIES


class FileScanner:
    """
    Scanner récursif des fichiers.
    """

    def __init__(self, root_directory: str):

        self.root_directory = Path(root_directory).resolve()

        self._validate_directory()

    

    def _validate_directory(self) -> None:
        """
        Vérifie que le dossier existe.
        """

        if not self.root_directory.exists():

            raise FileNotFoundError(

                f"Dossier introuvable : {self.root_directory}"

            )

        if not self.root_directory.is_dir():

            raise NotADirectoryError(

                f"Ce chemin n'est pas un dossier : "
                f"{self.root_directory}"

            )

    

    def scan(self) -> list[Path]:
        """
        Retourne la liste complète des fichiers.
        """

        return list(self.scan_generator())

    

    def scan_generator(self) -> Generator[Path, None, None]:
        """
        Parcourt récursivement tous les fichiers.

        Yields
        ------
        Path
        """

        for path in self.root_directory.rglob("*"):

            if path.is_dir():

                if path.name in IGNORED_DIRECTORIES:

                    continue

            if path.is_file():

                yield path.resolve()

    

    def count_files(self) -> int:

        return sum(1 for _ in self.scan_generator())

    

    def total_size(self) -> int:

        total = 0

        for file in self.scan_generator():

            try:

                total += file.stat().st_size

            except OSError:

                continue

        return total

    

    def get_extensions(self) -> dict[str, int]:

        extensions = {}

        for file in self.scan_generator():

            extension = file.suffix.lower()

            if extension == "":

                extension = "(sans extension)"

            extensions[extension] = (

                extensions.get(extension, 0) + 1

            )

        return dict(

            sorted(

                extensions.items(),

                key=lambda item: item[0]

            )

        )

    

    def get_largest_file(self) -> Path | None:

        largest = None

        largest_size = -1

        for file in self.scan_generator():

            try:

                size = file.stat().st_size

                if size > largest_size:

                    largest_size = size

                    largest = file

            except OSError:

                continue

        return largest

    

    def get_smallest_file(self) -> Path | None:

        smallest = None

        smallest_size = None

        for file in self.scan_generator():

            try:

                size = file.stat().st_size

                if smallest_size is None or size < smallest_size:

                    smallest_size = size

                    smallest = file

            except OSError:

                continue

        return smallest

    

    def summary(self) -> dict:

        return {

            "directory": str(self.root_directory),

            "files": self.count_files(),

            "total_size": self.total_size(),

            "extensions": self.get_extensions(),

            "largest_file": self.get_largest_file(),

            "smallest_file": self.get_smallest_file()

        }

    

    def print_summary(self) -> None:

        info = self.summary()

        print()

        print("=" * 60)

        print("SCANNER")

        print("=" * 60)

        print(f"Dossier        : {info['directory']}")

        print(f"Fichiers       : {info['files']}")

        print(f"Taille totale  : {info['total_size']} octets")

        print()

        print("Extensions :")

        for extension, total in info["extensions"].items():

            print(f"{extension:<15} {total}")

        print()

        if info["largest_file"]:

            print(

                "Plus gros fichier :",

                info["largest_file"]

            )

        if info["smallest_file"]:

            print(

                "Plus petit fichier :",

                info["smallest_file"]

            )

        print("=" * 60)
