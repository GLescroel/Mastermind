package GLescroel.myGames;

import static GLescroel.myGames.Log.TRACE;
import static GLescroel.myGames.Tools.convertArrayToString;

/**
 * Classe abstraite Jeu
 * contient les paramètres d'un jeu et la méthode d'affichage du résultat de la partie
 */
public abstract class Jeu{

    protected String nomJeu;
    protected String mode;
    protected int nbDigit;
    protected int nbValeur;
    protected int nbEssai;

    protected JoueurOrdi ordi;
    protected JoueurHumain joueur;

    public static final String nomMasterMind = "MASTERMIND";
    public static final String nomPlusMoins = "PLUSMOINS";

    public static final String modeChallenger = "Challenger";
    public static final String modeDefenseur = "Defenseur";
    public static final String modeDuel = "Duel";


    //// Constructor
    public Jeu(String nomJeu, JoueurHumain joueur, JoueurOrdi ordi, String mode, int nbDigit, int nbEssai) {
        TRACE("Jeu() (constructor)");
        this.nomJeu = nomJeu;
        this.mode = mode;
        this.nbDigit = nbDigit;
        this.nbEssai = nbEssai;
        this.joueur = joueur;
        this.ordi = ordi;
    }

    //////////////////// GETTER & SETTER//////////////////////////////////////////////////////////

    public String getNomJeu() { return nomJeu; }
    public void setNomJeu(String nomJeu) { this.nomJeu = nomJeu; }
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public int getNbDigit() { return nbDigit; }
    public void setNbDigit(int nbDigit) { this.nbDigit = nbDigit; }
    public int getNbEssai() { return nbEssai; }
    public void setNbEssai(int nbEssai) { this.nbEssai = nbEssai; }
    public int getNbValeur() { return nbValeur; }
    public void setNbValeur(int nbValeur) { this.nbValeur = nbValeur; }
    public JoueurOrdi getOrdi() { return ordi; }
    public void setOrdi(JoueurOrdi ordi) { this.ordi = ordi; }
    public JoueurHumain getJoueur() { return joueur; }
    public void setJoueur(JoueurHumain joueur) { this.joueur = joueur; }

    //////////////////// fin GETTER & SETTER//////////////////////////////////////////////////////////

    protected void affichageResultat(JoueurHumain joueur, JoueurOrdi ordi, String mode) {
        TRACE("Jeu.affichageResultat()");

        if(mode.equals(modeChallenger))
        {
            if(joueur.isCombinaisonTrouvee())
                System.out.println("Vous avez gagné !");
            else {
                System.out.println("Vous avez perdu !");
                System.out.println("La combinaison secrète de l'ordinateur était " + convertArrayToString(ordi.getCombinaisonSecrete()));
            }
        }
        else if(mode.equals(modeDefenseur))
        {
            if(ordi.isCombinaisonTrouvee())
                System.out.println("L'ordinateur a gagné !");
            else
                System.out.println("L'ordinateur a perdu !");
        }
        else if(mode.equals(modeDuel)) {
            if (joueur.isCombinaisonTrouvee() && ordi.isCombinaisonTrouvee())
                System.out.println("Egalité !");
            else if (!joueur.isCombinaisonTrouvee() && !ordi.isCombinaisonTrouvee())
            {
                System.out.println("Aucun gagnant !");
                System.out.println("La combinaison secrète de l'ordinateur était " + convertArrayToString(ordi.getCombinaisonSecrete()));
            }
            else if (joueur.isCombinaisonTrouvee())
                System.out.println("Vous avez gagné !");
            else if (ordi.isCombinaisonTrouvee())
                System.out.println("L'ordinateur a gagné !");
        }
    }

}
