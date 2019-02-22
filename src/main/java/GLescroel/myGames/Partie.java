package GLescroel.myGames;

import java.util.List;

/**
 * Classe Partie
 * récupère les paramètres possibles,
 * demande au joueur de les définir les paramètres de la partie
 * et lance le jeu en fonction de ces paramètres
 */
public class Partie {

    private String nomJeu;
    private String mode;
    private int nbEssai;
    private int nbDigit;
    private Parametres parametres;

    JoueurHumain joueur;
    JoueurOrdi ordi;

    /**
     * Constructor de la classe Partie
     * lance la récupération des paramètres du fichier paramètres
     */
    public Partie() {
        parametres = new Parametres();
        parametres.runInit();
    }


    /**
     * startPartie() lance l'initialisation de la partie (choix utilisateur), l'initialisation des joueurs et lance le jeu
     */
    public void startPartie(){
            initPartie();
            initJoueurs();
            runJeu();
    }

    /**
     * initJoueurs() initialise le joueur humain et le joueur ordinateur
     */
    public void initJoueurs() {
        joueur = new JoueurHumain(Interaction.askText("prénom"));
        ordi = new JoueurOrdi("Ordi");

    }

    /**
     * initPartie() demande à l'utilisateur de choisir le jeu, le mode, le nombre de caractères et le nombre d'essais
     * @see Partie#choisirJeu
     * @see Partie#choisirMode
     * @see Partie#choisirNbDigit
     * @see Partie#choisirNbEssai
     */
    public void initPartie(){

        choisirJeu(parametres.getJeuxPossibles());
        choisirMode(parametres.getModesPossibles());
        choisirNbDigit(parametres.getNbDigitMinPossible(), parametres.getNbDigitMaxPossible());
        choisirNbEssai(parametres.getNbEssaisMinPossible(), parametres.getNbEssaisMaxPossible());
    }

    /**
     * choisirJeu() permet au joueur de choisir le jeu auquel il souhaite jouer
     * @param listeJjeuxPossibles liste des jeux possibles à proposer au joueur
     */
    public void choisirJeu(List<String> listeJjeuxPossibles)
    {
        String[] jeuxPossibles = new String[listeJjeuxPossibles.size()];
        for(int j = 0 ; j < listeJjeuxPossibles.size() ; j++)
            jeuxPossibles[j] = listeJjeuxPossibles.get(j);

        int jeuChoisi = Interaction.askSomething("Partie", jeuxPossibles)-1;
        nomJeu = listeJjeuxPossibles.get(jeuChoisi);

        //System.out.println("jeux choisi : " + nomJeu);
    }

    /**
     * choisirMode() permet au joueur de choisir le mode dans lequel il souhaite jouer
     * @param listeModesPossibles liste des modes de jeu possibles
     */
    public void choisirMode(List<String> listeModesPossibles)
    {
        String[] modesPossibles = new String[listeModesPossibles.size()];
        for(int j = 0 ; j < listeModesPossibles.size() ; j++)
            modesPossibles[j] = listeModesPossibles.get(j);

        int modeChoisi = Interaction.askSomething("Mode", modesPossibles)-1;
        mode = listeModesPossibles.get(modeChoisi);

        //System.out.println("Mode choisi : " + mode);
    }

    /**
     * choisirNbDigit() permet au joueur de choisir le nombre de caractère des combinaisons
     * @param nbDigitMin nombre minimal possible de caractères des combinaisons
     * @param nbDigitMax nombre maximal possible de caractères des combinaisons
     */
    public void choisirNbDigit(int nbDigitMin, int nbDigitMax)
    {
        nbDigit = Interaction.askQuantity("de digit", nbDigitMin, nbDigitMax);
        //System.out.println("Nb digit choisi : " + nbDigit);
    }

    /**
     * choisirNbEssai() permet au joueur de choisir le nombre d'essai max pour trouver la combinaison secrète
     * @param nbEssaisMin nombre minimal possible d'essais pour trouver la combinaison secrète
     * @param nbEssaisMax nombre minimal possible d'essais pour trouver la combinaison secrète
     */
    public void choisirNbEssai(int nbEssaisMin, int nbEssaisMax)
    {
        nbEssai = Interaction.askQuantity("d'essais", nbEssaisMin, nbEssaisMax);
        //System.out.println("Nb essais choisi : " + nbEssai);
    }


    /**
     * runJeu() lance le jeu choisi dans le mode choisi avec les paramètres choisis
     */
    public void runJeu() {

        if(nomJeu.equals("MASTERMIND"))
        {
            Mastermind myMastermind = new Mastermind(nomJeu, joueur, ordi, mode, nbDigit, nbEssai);
            myMastermind.runMastermind();
        }
        else if(nomJeu.equals("PLUSMOINS"))
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
    /////////////// fin GETTER & SETTER //////////////////////////////

}
