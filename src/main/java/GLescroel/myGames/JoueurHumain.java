package GLescroel.myGames;

import static GLescroel.myGames.Log.TRACE;

/**
 * Classe JoueurHumain
 * étend la classe Joueur pour instancier un joueur humain
 */
public class JoueurHumain extends Joueur {

    // CONSTRUCTOR
    public JoueurHumain() {
        TRACE("JoueurHumain() (constructor)");
        setCombinaisonTrouvee(false);
    }

    /**
     * joueurChoisitCombiSecrete() = le joueur choisit sa combinaison secrète
     * @param nbDigit nombre de caractères de la combinaison
     * @param nbValeur nombre de valeurs possibles pour chaque caractère de la combinaison
     * @return String[] combinaison proposée par le joueur
     * @see Joueur#joueurProposeCombi(String, int, int)
     */
    @Override
    protected String[] joueurChoisitCombiSecrete(int nbDigit, int nbValeur){
        TRACE("joueurHumain.joueurChoisitCombiSecrete()");
        System.out.println("Choisissez votre combinaison secrète : ");
        return joueurProposeCombi(null, nbDigit, nbValeur);
    }

    /**
     * joueurProposeCombi() = le joueur saisit une combinaison (pour proposition ou définition de sa combinaison secrète)
     * @param nomJeu nom du jeu en cours
     * @param nbDigit nombre de caractères de la combinaison
     * @param nbValeur nombre de valeurs possibles pour chaque caractère de la combinaison
     * @return String[] combinaison saisie par le joueur
     * @see Interaction#askCombinaison(int, int)
     */
    @Override
    protected String[] joueurProposeCombi(String nomJeu, int nbDigit, int nbValeur){
        TRACE("joueurHumain.joueurProposeCombi()");
        return Interaction.askCombinaison(nbDigit, nbValeur);
    }

}
