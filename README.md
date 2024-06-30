### Description du Projet:

**Nom du Projet**: INSECT WARFARE

**Vue d'ensemble**:
Le projet "Insect Warfare" est un jeu développé en Java qui vise à offrir une expérience de jeu d'aventure dans un environnement interactif. Le joueur contrôle un personnage principal et explore une petite ile rempli de PNJ (Personnages Non Joueurs), d'objets interactifs.

**Composants Principaux**:

1. **Classes Principales**:
   - **Panel**: Représente le panneau principal du jeu, responsable du rendu et de la gestion des éléments visuels.
   - **Entity**: Classe de base pour les entités dans le jeu, comme le joueur, les PNJ et les insectes. Gère le mouvement, les collisions, les animations et les interactions de base.
   - **Combat**: Classe qui implémente un mini-jeu de pierre-papier-ciseaux entre le joueur et les adversaires.

2. **Fonctionnalités**:
   - **Mouvement et Animation**: Les entités (y compris le joueur et les PNJ) peuvent se déplacer dans les quatre principales directions (haut, bas, gauche, droite) et sont animées en conséquence.
   - **Collision**: Détection des collisions entre les entités, les objets et l'environnement (tuiles de la carte), assurant que le joueur et les PNJ ne traversent pas les barrières.
   - **Interactivité**: Possibilité d'interagir avec des objets tels que des clés et des portes, nécessaires pour progresser dans le jeu.
   - **Dialogues**: Support pour les dialogues entre le joueur et les PNJ, avec des transitions de direction basées sur les actions du joueur.
   - **Mini-Jeu de Combat**: Implémentation d'un mini-jeu de pierre-papier-ciseaux entre le joueur et les adversaires, avec détermination des résultats basée sur les choix des joueurs.

3. **Composants de Conception**:
   - **Carte de Tuiles**: Utilisation de cartes de tuiles pour représenter l'environnement du jeu de manière structurée et efficace.
   - **Sprites et Graphiques**: Utilisation d'images (sprites) pour représenter les personnages et les objets dans le jeu.
   - **Logique de Jeu**: Implémentation d'une logique de jeu robuste pour gérer les états et les événements

**Commandes**:
-Z: HAUT 
-S: BAS
-Q: Gauche
-D: Droite
-P: Pause
-Enter: INTERATION

**Technologies Utilisées**:
- Langage de Programmation: Java
- Bibliothèques: AWT (Abstract Window Toolkit) pour la manipulation graphique de base.

**Conclusion**:
Le projet "Insect Warfare" représente un effort pour créer un jeu d'aventure en Java avec des mécaniques solides de mouvement et d'interaction.
