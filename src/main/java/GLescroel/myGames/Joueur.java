package GLescroel.myGames;

/**
 * Classe abstraite Joueur
 * contient les paramètres d'un joueur
 * et les méthodes pour que le joueur définisse sa combinaison secrète et propose une combinaison
 */
public abstract class Joueur {


    private String[] combinaisonSecrete;
    private boolean combinaisonTrouvee;

    protected abstract String[] joueurChoisitCombiSecrete(int nbDigit, int nbValeur);
    protected abstract String[] joueurProposeCombi(String nomJeu, int nbDigit, int nbValeur);



        //////////////////// GETTER & SETTER//////////////////////////////////////////////////////////
    public String[] getCombinaisonSecrete() { return combinaisonSecrete; }
    public void setCombinaisonSecrete(String[] combinaisonSecrete) { this.combinaisonSecrete = combinaisonSecrete; }
    public boolean isCombinaisonTrouvee() { return combinaisonTrouvee; }
    public void setCombinaisonTrouvee(boolean combinaisonTrouvee) { this.combinaisonTrouvee = combinaisonTrouvee; }
    //////////////////// fin GETTER & SETTER//////////////////////////////////////////////////////////


}
