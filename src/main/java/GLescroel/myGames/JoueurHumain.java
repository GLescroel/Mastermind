package GLescroel.myGames;

/**
 * Classe JoueurHumain
 * étend la classe Joueur pour instancier un joueur humain
 */
public class JoueurHumain extends Joueur {

    // CONSTRUCTOR
    public JoueurHumain() {
        setCombinaisonTrouvee(false);
    }

    /**
     * joueurChoisitCombiSecrete() = le joueur choisit sa combinaison secrète
     * @param nbDigit nombre de caractères de la combinaison
     * @return String[] combinaison proposée par le joueur
     * @see Joueur#joueurProposeCombi(int, int)
     */
    @Override
    protected String[] joueurChoisitCombiSecrete(int nbDigit, int nbValeur){
        System.out.println("Choisissez votre combinaison secrète : ");
        return joueurProposeCombi(nbDigit, nbValeur);
    }

    /**
     * joueurProposeCombi() = le joueur saisit une combinaison (pour proposition ou définition de sa combinaison secrète)
     * @param nbDigit nombre de caractères de la combinaison
     * @return String[] combinaison saisie par le joueur
     * @see Interaction#askCombinaison(int, int)
     */
    @Override
    protected String[] joueurProposeCombi(int nbDigit, int nbValeur){
        return Interaction.askCombinaison(nbDigit, nbValeur);
    }

}
