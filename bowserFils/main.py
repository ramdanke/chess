"""

main.py



Auteur : ramdane

"""

from __future__ import annotations

import sys

from cli import execute


def main() -> int:
    """
    Point d'entrée principal.
    """

    try:

        execute()

        return 0

    except KeyboardInterrupt:

        print("\n\nProgramme interrompu par l'utilisateur.")

        return 1

    except Exception as error:

        print(f"\nErreur : {error}")

        return 1


if __name__ == "__main__":

    sys.exit(main())
