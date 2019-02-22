package GLescroel.myGames;

/**
 * Classe JoueurHumain
 * étend la classe Joueur pour instancier un joueur humain
 */
public class JoueurHumain extends Joueur {

    // CONSTRUCTOR
    public JoueurHumain(String nomJoueur) {
        this.setEstHumain(true);
        this.setNomJoueur(nomJoueur);
        setCombinaisonTrouvee(false);
    }

    /**
     * joueurChoisitCombiSecrete() = le joueur choisit sa combinaison secrète
     * @param nbDigit nombre de caractères de la combinaison
     * @return String[] combinaison proposée par le joueur
     * @see Joueur#joueurProposeCombi(int)
     */
    @Override
    protected String[] joueurChoisitCombiSecrete(int nbDigit){
        System.out.println("Choisissez votre combinaison secrète : ");
        return joueurProposeCombi(nbDigit);
    }

    /**
     * joueurProposeCombi() = le joueur saisit une combinaison (pour proposition ou définition de sa combinaison secrète)
     * @param nbDigit nombre de caractères de la combinaison
     * @return String[] combinaison saisie par le joueur
     * @see Interaction#askCombinaison(int) askCombinaison()
     */
    @Override
    protected String[] joueurProposeCombi(int nbDigit){
        return Interaction.askCombinaison(nbDigit);
    }

}
