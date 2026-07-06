# File Search Engine

## Description

File Search Engine est un moteur de recherche de fichiers développé en Python pour Linux.

Le programme permet d'indexer rapidement les fichiers d'un dossier dans une base de données SQLite afin d'effectuer des recherches rapides selon plusieurs critères.

Le projet utilise le multithreading pour accélérer l'indexation.

---

## Fonctionnalités

- Indexation récursive d'un dossier
- Mise à jour de l'index
- Reconstruction complète de l'index
- Recherche par nom
- Recherche par contenu
- Recherche par extension
- Recherche par chemin
- Recherche par taille
- Recherche globale
- Affichage des statistiques
- Affichage des statistiques par extension
- Affichage des derniers fichiers indexés
- Suppression complète de l'index

---

## Technologies utilisées

- Python 3
- SQLite
- pathlib
- argparse
- logging
- concurrent.futures (ThreadPoolExecutor)
- hashlib
- mimetypes

---

## Structure du projet

```
bowserFils/
│
├── main.py
├── cli.py
├── config.py
├── database.py
├── scanner.py
├── indexer.py
├── search.py
├── utils.py
│
├── database/
│   └── files.db
│
├── logs/
│   └── application.log
│
├── README.md
└── requirements.txt
```

---

## Installation

Cloner le projet :

```bash
git clone <url_du_projet>
```

Accéder au dossier :

```bash
cd bowserFils
```

---

## Utilisation

### Indexer un dossier

```bash
python3 main.py --index /chemin/du/dossier
```

### Mettre à jour l'index

```bash
python3 main.py --update /chemin/du/dossier
```

### Reconstruire complètement l'index

```bash
python3 main.py --rebuild /chemin/du/dossier
```

### Recherche par nom

```bash
python3 main.py --name rapport
```

### Recherche par contenu

```bash
python3 main.py --content python
```

### Recherche par extension

```bash
python3 main.py --extension .pdf
```

### Recherche globale

```bash
python3 main.py --search moteur
```

### Afficher les statistiques

```bash
python3 main.py --stats
```

### Afficher tous les fichiers

```bash
python3 main.py --list
```

### Nombre de fichiers indexés

```bash
python3 main.py --count
```

### Supprimer l'index

```bash
python3 main.py --clear
```

---

## Fonctionnement

Le programme fonctionne en quatre étapes :

1. Scan récursif du dossier.
2. Lecture des métadonnées et du contenu des fichiers.
3. Indexation dans une base SQLite.
4. Recherche rapide dans la base de données.

---

## Architecture

```
main.py
    │
    ▼
cli.py
    │
    ├──────────────┐
    ▼              ▼
indexer.py     search.py
    │              │
    └──────┬───────┘
           ▼
      database.py
           ▲
           │
     scanner.py
           ▲
           │
        utils.py
```

---

## Auteur

ramdane