PROJET : CHESS

1. DESCRIPTION
   
   Ce projet est un jeu de Xiangqi (échecs chinois) développé en Java avec une interface graphique Swing.

Fonctionnalités :

* Jeu joueur vs joueur
* Jeu joueur vs ordinateur (3 niveaux : facile, moyen, difficile)
* Affichage graphique du plateau et des pièces
* Historique des coups
* Gestion des captures
* Détection échec et échec et mat

2. PRÉREQUIS


* Java JDK 1.8 ou supérieur
* Eclipse IDE


3. IMPORTER LE PROJET


1. Ouvrir Eclipse
2. File → Import
3. Existing Projects into Workspace
4. Sélectionner le dossier du projet
5. Valider


4. CONFIGURATION


Les librairies nécessaires sont fournies par l’enseignant.
Il faut les ajouter dans Eclipse :

Project → Properties → Java Build Path → Libraries → Add External JARs

Ajouter :

* log4j-1.2.17.jar


5. LANCER LE PROGRAMME


1. Aller dans le package : gui
2. Lancer la classe : MainGUI.java
3. Clique droit → Run As → Java Application


6. STRUCTURE DU PROJET


* gui : interface graphique
* engine.map : gestion du plateau
* engine.mobile : gestion des pièces
* engine.process : logique du jeu + bot
* log : configuration Log4j


7. REMARQUES


* Les images sont dans le dossier /images
* Le projet fonctionne sans modification si les librairies sont ajoutées correctement
* Aucun fichier externe n’est requis en dehors des librairies fournies

AUTEURS


* KETFI Ramdane
* Sow Mamadou Saliou
* Djermoune Thanina

Licence 2 Informatique – CY Cergy Paris Université
