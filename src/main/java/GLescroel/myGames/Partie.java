package GLescroel.myGames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static GLescroel.myGames.Log.TRACE;
import static GLescroel.myGames.Main.MODERUN;
import static GLescroel.myGames.Main.myLogger;
import static GLescroel.myGames.Parametres.runModeDebug;

/**
 * Classe Partie
 * récupère les paramètres possibles,
 * demande au joueur de les définir les paramètres de la partie
 * et lance le jeu en fonction de ces paramètres
 */
public class Partie {

    private String nomJeu;
    private String mode;
    private int nbDigit;
    private int nbValeur;
    private int nbEssai;
    private Parametres parametres;

    JoueurHumain joueur;
    JoueurOrdi ordi;

    private final String[] suitePartie = {
            "Rejouer la même partie",
            "Lancer une nouvelle partie",
            "Quitter"};
    private final int rejouer = 0;
    private final int nouvellePartie = 1;
    private final int quitter = 2;

    public static final Logger myDevLogger = LogManager.getLogger(GLescroel.myGames.Partie.class.getName());

    /**
     * Constructor de la classe Partie
     * lance la récupération des paramètres du fichier paramètres
     */
    public Partie() {
        if(MODERUN.equals(runModeDebug))
            myLogger.trace("----------Partie() (constructor)----------");

        parametres = new Parametres();
        parametres.runInit();
    }


    /**
     * startPartie() lance l'initialisation de la partie (choix utilisateur), l'initialisation des joueurs et lance le jeu
     */
    public void startPartie(){
        TRACE("Partie.startPartie()");

        String suiteChoisie = "";
            do {
                initPartie();
                initJoueurs();

                do {
                    runJeu();


                    String demande = "\nLa partie est terminée\nQue vouhaitez vous faire maintenant ?";
                    suiteChoisie = suitePartie[Interaction.askSomething(demande, suitePartie)-1];

                } while (suiteChoisie.equals(suitePartie[rejouer]));

            }while(suiteChoisie.equals(suitePartie[nouvellePartie]));

        System.out.println("Merci d'avoir joué ! à bientôt !");

    }

    /**
     * initJoueurs() initialise le joueur humain et le joueur ordinateur
     */
    public void initJoueurs() {
        TRACE("Partie.initJoueurs()");

        joueur = new JoueurHumain();
        ordi = new JoueurOrdi();
    }

    /**
     * initPartie() demande à l'utilisateur de choisir le jeu, le mode, le nombre de caractères et le nombre d'essais
     * @see Partie#choisirJeu
     * @see Partie#choisirMode
     * @see Partie#choisirNbDigit
     * @see Partie#choisirNbEssai
     */
    public void initPartie(){
        TRACE("Partie.initPartie()");

        choisirJeu(parametres.getJeuxPossibles());
        choisirMode(parametres.getModesPossibles());
        choisirNbDigit(parametres.getNbDigitMinPossible(), parametres.getNbDigitMaxPossible());

        if(nomJeu.equals(Jeu.nomMasterMind))
            choisirNbValeur(parametres.getNbValeursMinPossible(), parametres.getNbValeursMaxPossible());
        else nbValeur = 10;

        choisirNbEssai(parametres.getNbEssaisMinPossible(), parametres.getNbEssaisMaxPossible());
    }

    /**
     * choisirJeu() permet au joueur de choisir le jeu auquel il souhaite jouer
     * @param listeJjeuxPossibles liste des jeux possibles à proposer au joueur
     */
    public void choisirJeu(List<String> listeJjeuxPossibles) {
        TRACE("Partie.choisirJeu()");

        String[] jeuxPossibles = new String[listeJjeuxPossibles.size()];
        for(int j = 0 ; j < listeJjeuxPossibles.size() ; j++)
            jeuxPossibles[j] = listeJjeuxPossibles.get(j);

        String demande = "à quel jeu souhaitez vous jouer ?";
        int jeuChoisi = Interaction.askSomething(demande, jeuxPossibles)-1;
        nomJeu = listeJjeuxPossibles.get(jeuChoisi);

        //DEBUG_DEV("jeux choisi : " + nomJeu);
    }

    /**
     * choisirMode() permet au joueur de choisir le mode dans lequel il souhaite jouer
     * @param listeModesPossibles liste des modes de jeu possibles
     */
    public void choisirMode(List<String> listeModesPossibles) {
        TRACE("Partie.choisirMode()");

        String[] modesPossibles = new String[listeModesPossibles.size()];
        for(int j = 0 ; j < listeModesPossibles.size() ; j++)
            modesPossibles[j] = listeModesPossibles.get(j);

        String demande = "\nDans quel mode souhaitez vous jouer ?";
        int modeChoisi = Interaction.askSomething(demande, modesPossibles)-1;
        mode = listeModesPossibles.get(modeChoisi);

        //DEBUG_DEV("Mode choisi : " + mode);
    }

    /**
     * choisirNbDigit() permet au joueur de choisir le nombre de caractère des combinaisons
     * @param nbDigitMin nombre minimal possible de caractères des combinaisons
     * @param nbDigitMax nombre maximal possible de caractères des combinaisons
     */
    public void choisirNbDigit(int nbDigitMin, int nbDigitMax) {
        TRACE("Partie.choisirNbDigit()");

        nbDigit = Interaction.askQuantity("de digit", nbDigitMin, nbDigitMax);
        //DEBUG_DEV("Nb digit choisi : " + nbDigit);
    }

    /**
     * choisirNbDigit() permet au joueur de choisir le nombre de caractère des combinaisons
     * @param nbValeursMin nombre minimal valeurs possibles pour chaque caractère
     * @param nbValeursMax nombre maximal valeurs possibles pour chaque caractère
     */
    public void choisirNbValeur(int nbValeursMin, int nbValeursMax) {
        TRACE("Partie.choisirNbValeur()");

        nbValeur = Interaction.askQuantity("de valeurs possibles", nbValeursMin, nbValeursMax);
        System.out.println("Nous allons donc jouer avec les chiffres entre 0 et " + (nbValeur-1));
    }

    /**
     * choisirNbEssai() permet au joueur de choisir le nombre d'essai max pour trouver la combinaison secrète
     * @param nbEssaisMin nombre minimal possible d'essais pour trouver la combinaison secrète
     * @param nbEssaisMax nombre minimal possible d'essais pour trouver la combinaison secrète
     */
    public void choisirNbEssai(int nbEssaisMin, int nbEssaisMax) {
        TRACE("Partie.choisirNbEssai()");

        nbEssai = Interaction.askQuantity("d'essais", nbEssaisMin, nbEssaisMax);
        //DEBUG_DEV("Nb essais choisi : " + nbEssai);
    }


    /**
     * runJeu() lance le jeu choisi dans le mode choisi avec les paramètres choisis
     */
    public void runJeu() {
        TRACE("Partie.runJeu()");

        if(nomJeu.equals(Jeu.nomMasterMind))
        {
            Mastermind myMastermind = new Mastermind(nomJeu, joueur, ordi, mode, nbDigit, nbValeur, nbEssai);
            myMastermind.runMastermind();
        }
        else if(nomJeu.equals(Jeu.nomPlusMoins))
        {
            PlusMoins myPlusMoins = new PlusMoins(nomJeu, joueur, ordi, mode, nbDigit, nbEssai);
            myPlusMoins.runPlusMoins();
        }
        else
            System.out.println("Ce jeu n'est pas implémenté");
    }


    /////////////// GETTER & SETTER //////////////////////////////
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public String getNomJeu() { return nomJeu; }
    public void setNomJeu(String nomJeu) { this.nomJeu = nomJeu; }
    public int getNbEssai() { return nbEssai; }
    public void setNbEssai(int nbEssai) { this.nbEssai = nbEssai; }
    public int getNbDigit() { return nbDigit; }
    public void setNbDigit(int nbDigit) { this.nbDigit = nbDigit; }
    public int getNbValeur() { return nbValeur; }
    public void setNbValeur(int nbValeur) { this.nbValeur = nbValeur; }
    /////////////// fin GETTER & SETTER //////////////////////////////

}
