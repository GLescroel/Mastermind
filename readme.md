Contexte : 
ce projet est l'implémentation d'un jeu proposant de jouer au Mastermind ou au PlusMoins pour un joueur humain contre l'ordinateur.
ce projet contient 
- un fichier de paramètres config.properties pour paramétrer :
    - les jeux disponibles (MASTERMIND / PLUSMOINS)
    - les modes de jeu disponibles (Challenger / défenseur / duel)
    - le nombre de digit min et max que le joueur peut choisir pour paramétrer son jeu
    - le nombre de valeurs min et max que le joueur peut choisir pour paramétrer son jeu de Mastermind
    - le nombre d'essais min et max que le joueur peut choisir pour paraméter son jeu
    - le mode d'exécution (DEBUG / DEV / PROD) : cette donnée peut également être envoyée en lançant le jeu en ligne de commande
- un fichier de paramétrage log4j2.xml pour paramétrer le loggin par log4j

---------------------------------------------------------------------------------------------------------------------------------

Pré-requis :
- java 1.8 minimum
- maven

---------------------------------------------------------------------------------------------------------------------------------

Build du projet :
en ligne de commande, se positionner dans le répertoire du projet et "mvn clean package"

---------------------------------------------------------------------------------------------------------------------------------

Exécution :
- en ligne de commande, se positionner dans le répertoire projet/target et 
- lancer "java -jar myGames.jar" pour utiliser le fichier de config qui exécute le jeu en mode PROD
ou 
- lancer "java -jar myGames.jar -[mode]" pour modifier le mode d'exécution avec mode
  - PROD : pas de trace ni affichage de debug
  - DEV : affichage console de messages de debug
  - DEBUG : trace dans le fichier Logs/myGames.log l'exécution de toutes les méthodes appelées pendant l'exécution

---------------------------------------------------------------------------------------------------------------------------------

Logs :
En mode DEBUG, les traces sont enregistrées dans le fichier Logs/MyGames.log

---------------------------------------------------------------------------------------------------------------------------------
