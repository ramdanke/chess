

PROGRAM_NAME = "File Search Engine"

VERSION = "2.0"

AUTHOR = "ramdane"

DESCRIPTION = (
    "Moteur de recherche de fichiers "
    "développé en Python."
)
from pathlib import Path
import os




BASE_DIR = Path(__file__).resolve().parent


DATABASE_DIR = BASE_DIR / "database"
DATABASE_DIR.mkdir(exist_ok=True)

DATABASE_PATH = DATABASE_DIR / "files.db"


LOG_DIR = BASE_DIR / "logs"
LOG_DIR.mkdir(exist_ok=True)

LOG_FILE = LOG_DIR / "application.log"


TEST_DIR = BASE_DIR / "tests"





IGNORED_DIRECTORIES = {

    ".git",

    "__pycache__",

    ".idea",

    ".vscode",

    "venv",

    ".venv",

    "node_modules"

}


TEXT_EXTENSIONS = {

    ".txt",

    ".csv",

    ".py",

    ".java",

    ".c",

    ".cpp",

    ".h",

    ".hpp",

    ".json",

    ".xml",

    ".html",

    ".css",

    ".js",

    ".md",

    ".ini",

    ".yaml",

    ".yml",

    ".log"

}



MAX_FILE_SIZE = 10 * 1024 * 1024



CPU_COUNT = os.cpu_count() or 4


MAX_THREADS = min(8, CPU_COUNT)



DATABASE_TIMEOUT = 30




MAX_RESULTS = 100



LOG_LEVEL = "INFO"

LOG_FORMAT = (
    "%(asctime)s | "
    "%(levelname)s | "
    "%(message)s"
)



PROGRAM_NAME = "File Search Engine"

VERSION = "1.0"

AUTHOR = "ramdane"

DESCRIPTION = (
    "Moteur de recherche de fichiers "
    "sous Linux développé en Python."
)
