package GLescroel.myGames;

/**
 * Classe abstraite Joueur
 * contient les paramètres d'un joueur
 * et les méthodes pour que le joueur définisse sa combinaison secrète et propose une combinaison
 */
public abstract class Joueur {

    private String nomJoueur;
    private boolean estHumain;

    private String[] combinaisonSecrete;
    private boolean combinaisonTrouvee;

    protected abstract String[] joueurChoisitCombiSecrete(int nbDigit);
    protected abstract String[] joueurProposeCombi(int nbDigit);



        //////////////////// GETTER & SETTER//////////////////////////////////////////////////////////
    public boolean isEstHumain() { return estHumain; }
    public String[] getCombinaisonSecrete() { return combinaisonSecrete; }
    public void setCombinaisonSecrete(String[] combinaisonSecrete) { this.combinaisonSecrete = combinaisonSecrete; }
    public boolean isCombinaisonTrouvee() { return combinaisonTrouvee; }
    public void setCombinaisonTrouvee(boolean combinaisonTrouvee) { this.combinaisonTrouvee = combinaisonTrouvee; }
    public String getNomJoueur() { return nomJoueur; }
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }
    public boolean isHumain() {
        return estHumain;
    }
    public void setEstHumain(boolean estHumain) {
        this.estHumain = estHumain;
    }
    //////////////////// fin GETTER & SETTER//////////////////////////////////////////////////////////


}
