"""

database.py


Gestion de la base de données SQLite.

Auteur : ramdane

"""

from __future__ import annotations

import sqlite3
import threading
from typing import List, Optional

from config import DATABASE_PATH, DATABASE_TIMEOUT


class Database:
    """
    Gestionnaire de la base SQLite.
    Compatible avec le multithreading.
    """

    def __init__(self) -> None:

        self.db_path = str(DATABASE_PATH)

        self.lock = threading.Lock()

        self.create_database()

    

    def connect(self) -> sqlite3.Connection:
        """
        Crée une connexion SQLite.
        """

        connection = sqlite3.connect(

            self.db_path,

            timeout=DATABASE_TIMEOUT

        )

        connection.row_factory = sqlite3.Row

        return connection

    

    def create_database(self) -> None:
        """
        Crée la table principale ainsi
        que les index SQL.
        """

        with self.connect() as connection:

            cursor = connection.cursor()

            cursor.execute(
                """
                CREATE TABLE IF NOT EXISTS files(

                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    path TEXT UNIQUE NOT NULL,

                    name TEXT NOT NULL,

                    extension TEXT,

                    size INTEGER,

                    modified REAL,

                    hash TEXT,

                    content TEXT

                )
                """
            )

            

            cursor.execute(
                """
                CREATE INDEX IF NOT EXISTS
                idx_name
                ON files(name)
                """
            )

            cursor.execute(
                """
                CREATE INDEX IF NOT EXISTS
                idx_extension
                ON files(extension)
                """
            )

            cursor.execute(
                """
                CREATE INDEX IF NOT EXISTS
                idx_modified
                ON files(modified)
                """
            )

            cursor.execute(
                """
                CREATE INDEX IF NOT EXISTS
                idx_size
                ON files(size)
                """
            )

            connection.commit()

    

    def insert_or_update(
        self,
        *,
        path: str,
        name: str,
        extension: str,
        size: int,
        modified: float,
        file_hash: str,
        content: str
    ) -> None:
        """
        Ajoute un fichier ou met à jour
        ses informations.
        """

        with self.lock:

            with self.connect() as connection:

                cursor = connection.cursor()

                cursor.execute(
                    """
                    INSERT INTO files(

                        path,

                        name,

                        extension,

                        size,

                        modified,

                        hash,

                        content

                    )

                    VALUES(?,?,?,?,?,?,?)

                    ON CONFLICT(path)

                    DO UPDATE SET

                        name=excluded.name,

                        extension=excluded.extension,

                        size=excluded.size,

                        modified=excluded.modified,

                        hash=excluded.hash,

                        content=excluded.content
                    """,

                    (

                        path,

                        name,

                        extension,

                        size,

                        modified,

                        file_hash,

                        content

                    )

                )

                connection.commit()

    

    def delete(self, path: str) -> None:

        with self.lock:

            with self.connect() as connection:

                connection.execute(

                    "DELETE FROM files WHERE path=?",

                    (path,)

                )

                connection.commit()

    

    def clear(self) -> None:

        with self.lock:

            with self.connect() as connection:

                connection.execute(

                    "DELETE FROM files"

                )

                connection.commit()

    

    def file_exists(self, path: str) -> bool:

        with self.connect() as connection:

            cursor = connection.execute(

                "SELECT id FROM files WHERE path=?",

                (path,)

            )

            return cursor.fetchone() is not None

    

    def count(self) -> int:

        with self.connect() as connection:

            cursor = connection.execute(

                "SELECT COUNT(*) FROM files"

            )

            return cursor.fetchone()[0]

    

    def get_all_paths(self) -> set[str]:

        with self.connect() as connection:

            cursor = connection.execute(

                "SELECT path FROM files"

            )

            return {

                row["path"]

                for row in cursor.fetchall()

            }

    

    def get_all_files(self) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                ORDER BY name
                """

            )

            return cursor.fetchall()
                

    def search_by_name(
        self,
        keyword: str
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE LOWER(name)

                LIKE LOWER(?)

                ORDER BY name
                """,

                (f"%{keyword}%",)

            )

            return cursor.fetchall()

    

    def search_by_content(
        self,
        keyword: str
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE LOWER(content)

                LIKE LOWER(?)

                ORDER BY name
                """,

                (f"%{keyword}%",)

            )

            return cursor.fetchall()

    

    def search_by_extension(
        self,
        extension: str
    ) -> List[sqlite3.Row]:

        if not extension.startswith("."):

            extension = "." + extension

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE LOWER(extension)=LOWER(?)

                ORDER BY name
                """,

                (extension,)

            )

            return cursor.fetchall()

    

    def search_by_path(
        self,
        keyword: str
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE LOWER(path)

                LIKE LOWER(?)

                ORDER BY path
                """,

                (f"%{keyword}%",)

            )

            return cursor.fetchall()

    

    def search_by_size(
        self,
        minimum_size: int
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE size >= ?

                ORDER BY size DESC
                """,

                (minimum_size,)

            )

            return cursor.fetchall()

    

    def search_all(
        self,
        keyword: str
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE

                    LOWER(name)

                    LIKE LOWER(?)

                OR

                    LOWER(path)

                    LIKE LOWER(?)

                OR

                    LOWER(content)

                    LIKE LOWER(?)

                ORDER BY name
                """,

                (

                    f"%{keyword}%",

                    f"%{keyword}%",

                    f"%{keyword}%"

                )

            )

            return cursor.fetchall()

    

    def get_statistics(self) -> dict:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT

                    COUNT(*) AS total_files,

                    SUM(size) AS total_size,

                    AVG(size) AS average_size,

                    MIN(size) AS minimum_size,

                    MAX(size) AS maximum_size

                FROM files
                """

            )

            row = cursor.fetchone()

            return dict(row)

    

    def get_extensions_statistics(
        self
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT

                    extension,

                    COUNT(*) AS total

                FROM files

                GROUP BY extension

                ORDER BY total DESC
                """

            )

            return cursor.fetchall()

    

    def get_recent_files(
        self,
        limit: int = 10
    ) -> List[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                ORDER BY modified DESC

                LIMIT ?
                """,

                (limit,)

            )

            return cursor.fetchall()

    

    def get_file(
        self,
        path: str
    ) -> Optional[sqlite3.Row]:

        with self.connect() as connection:

            cursor = connection.execute(

                """
                SELECT *

                FROM files

                WHERE path=?
                """,

                (path,)

            )

            return cursor.fetchone()

    

    def delete_by_extension(
        self,
        extension: str
    ) -> None:

        if not extension.startswith("."):

            extension = "." + extension

        with self.lock:

            with self.connect() as connection:

                connection.execute(

                    """
                    DELETE

                    FROM files

                    WHERE LOWER(extension)=LOWER(?)
                    """,

                    (extension,)

                )

                connection.commit()

    

    def close(self) -> None:
        """
        Les connexions SQLite sont ouvertes
        et fermées automatiquement avec
        le contexte 'with'.

        Cette méthode est conservée
        pour compatibilité.
        """

        pass
        
        
        def get_file(self, path: str):

            with self.connect() as connection:

                cursor = connection.execute(

                    """
                    SELECT *

                    FROM files

                    WHERE path = ?
                    """,

                    (path,)

            )

            return cursor.fetchone()
