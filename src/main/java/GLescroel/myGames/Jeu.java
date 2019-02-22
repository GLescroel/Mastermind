package GLescroel.myGames;

/**
 * Classe abstraite Jeu
 * contient les paramètres d'un jeu et la méthode d'affichage du résultat de la partie
 */
public abstract class Jeu{

    protected String nomJeu;
    protected String mode;
    protected int nbDigit;
    protected int nbEssai;
    protected JoueurOrdi ordi;
    protected JoueurHumain joueur;

    //// Constructor
    public Jeu(String nomJeu, JoueurHumain joueur, JoueurOrdi ordi, String mode, int nbDigit, int nbEssai) {
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
    public JoueurOrdi getOrdi() { return ordi; }
    public void setOrdi(JoueurOrdi ordi) { this.ordi = ordi; }
    public JoueurHumain getJoueur() { return joueur; }
    public void setJoueur(JoueurHumain joueur) { this.joueur = joueur; }

    //////////////////// fin GETTER & SETTER//////////////////////////////////////////////////////////

    protected void affichageResultat(JoueurHumain joueur, JoueurOrdi ordi, String mode) {

        if(mode.equals("Challenger"))
        {
            if(joueur.isCombinaisonTrouvee())
                System.out.println("Vous avez gagné !");
            else
                System.out.println("Vous avez perdu !");
        }
        else if(mode.equals("Defenseur"))
        {
            if(ordi.isCombinaisonTrouvee())
                System.out.println("L'ordinateur a gagné !");
            else
                System.out.println("L'ordinateur a perdu !");
        }
        else if(mode.equals("Duel")) {
            if (joueur.isCombinaisonTrouvee() && ordi.isCombinaisonTrouvee())
                System.out.println("Egalité !");
            else if (!joueur.isCombinaisonTrouvee() && !ordi.isCombinaisonTrouvee())
                System.out.println("Aucun gagnant !");
            else if (joueur.isCombinaisonTrouvee())
                System.out.println("Vous avez gagné !");
            else if (ordi.isCombinaisonTrouvee())
                System.out.println("L'ordinateur a gagné !");
        }
    }

}
