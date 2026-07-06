"""
utils.py

Projet : File Search Engine

Fonctions utilitaires.

Auteur : ramdane

"""

from __future__ import annotations

import hashlib
import mimetypes

from pathlib import Path
from datetime import datetime

from config import (
    TEXT_EXTENSIONS,
    MAX_FILE_SIZE
)




def get_file_name(file_path: Path) -> str:
    """
    Retourne le nom du fichier.
    """

    return file_path.name




def get_extension(file_path: Path) -> str:
    """
    Retourne l'extension en minuscules.
    """

    return file_path.suffix.lower()




def get_file_size(file_path: Path) -> int:
    """
    Retourne la taille en octets.
    """

    return file_path.stat().st_size




def get_last_modified(file_path: Path) -> float:
    """
    Timestamp de la dernière modification.
    """

    return file_path.stat().st_mtime


def get_last_modified_string(file_path: Path) -> str:
    """
    Date lisible.
    """

    timestamp = get_last_modified(file_path)

    return datetime.fromtimestamp(timestamp).strftime(

        "%d/%m/%Y %H:%M:%S"

    )




def get_mime_type(file_path: Path) -> str:

    mime, _ = mimetypes.guess_type(file_path)

    return mime or "application/octet-stream"




def is_text_file(file_path: Path) -> bool:
    """
    Vérifie si le fichier peut être indexé.
    """

    extension = get_extension(file_path)

    if extension in TEXT_EXTENSIONS:

        return True

    mime = get_mime_type(file_path)

    return mime.startswith("text")




def is_file_too_large(file_path: Path) -> bool:

    return get_file_size(file_path) > MAX_FILE_SIZE




def read_text_file(file_path: Path) -> str:
    """
    Lit un fichier texte.

    Retourne une chaîne vide
    en cas d'erreur.
    """

    if not is_text_file(file_path):

        return ""

    if is_file_too_large(file_path):

        return ""

    encodings = (

        "utf-8",

        "latin-1",

        "cp1252"

    )

    for encoding in encodings:

        try:

            with open(

                file_path,

                "r",

                encoding=encoding,

                errors="ignore"

            ) as file:

                return file.read()

        except Exception:

            continue

    return ""




def calculate_sha256(file_path: Path) -> str:
    """
    Calcule le SHA256 du fichier.
    """

    sha = hashlib.sha256()

    try:

        with open(file_path, "rb") as file:

            while True:

                block = file.read(8192)

                if not block:

                    break

                sha.update(block)

    except OSError:

        return ""

    return sha.hexdigest()


def human_size(size: int) -> str:
    """
    Convertit une taille en une chaîne lisible.
    """

    units = [

        "B",

        "KB",

        "MB",

        "GB",

        "TB"

    ]

    value = float(size)

    for unit in units:

        if value < 1024:

            return f"{value:.2f} {unit}"

        value /= 1024

    return f"{value:.2f} PB"




def file_exists(file_path: Path) -> bool:
    """
    Vérifie si un fichier existe.
    """

    return file_path.exists() and file_path.is_file()




def is_readable(file_path: Path) -> bool:
    """
    Vérifie si le fichier est lisible.
    """

    try:

        with open(file_path, "rb"):

            return True

    except OSError:

        return False




def clean_text(text: str) -> str:
    """
    Nettoie le texte avant indexation.
    """

    if not text:

        return ""

    return " ".join(text.split())




def get_file_information(file_path: Path) -> dict:
    """
    Retourne toutes les informations utiles
    d'un fichier.
    """

    content = read_text_file(file_path)

    return {

        "name": get_file_name(file_path),

        "path": str(file_path.resolve()),

        "extension": get_extension(file_path),

        "size": get_file_size(file_path),

        "modified": get_last_modified(file_path),

        "modified_string": get_last_modified_string(file_path),

        "mime": get_mime_type(file_path),

        "hash": calculate_sha256(file_path),

        "content": clean_text(content)

    }




def print_file_information(file_path: Path) -> None:
    """
    Affiche les informations d'un fichier.
    """

    if not file_exists(file_path):

        print("Fichier introuvable.")

        return

    info = get_file_information(file_path)

    print()

    print("=" * 60)

    print("INFORMATIONS FICHIER")

    print("=" * 60)

    print(f"Nom           : {info['name']}")

    print(f"Chemin        : {info['path']}")

    print(f"Extension     : {info['extension']}")

    print(f"Type MIME     : {info['mime']}")

    print(f"Taille        : {human_size(info['size'])}")

    print(f"Modification  : {info['modified_string']}")

    print(f"SHA256        : {info['hash']}")

    print("=" * 60)




def can_be_indexed(file_path: Path) -> bool:
    """
    Vérifie si le fichier peut être indexé.
    """

    return (

        file_exists(file_path)

        and

        is_readable(file_path)

        and

        not is_file_too_large(file_path)

    )




def filter_indexable_files(
    files: list[Path]
) -> list[Path]:
    """
    Retourne uniquement les fichiers indexables.
    """

    return [

        file

        for file in files

        if can_be_indexed(file)

    ]




def calculate_total_size(
    files: list[Path]
) -> int:
    """
    Calcule la taille totale d'une liste de fichiers.
    """

    total = 0

    for file in files:

        try:

            total += get_file_size(file)

        except OSError:

            continue

    return total




def count_text_files(
    files: list[Path]
) -> int:
    """
    Retourne le nombre de fichiers texte.
    """

    return sum(

        1

        for file in files

        if is_text_file(file)

    )




def summary(
    files: list[Path]
) -> dict:
    """
    Retourne un résumé statistique.
    """

    return {

        "total_files": len(files),

        "text_files": count_text_files(files),

        "total_size": calculate_total_size(files)

    }
