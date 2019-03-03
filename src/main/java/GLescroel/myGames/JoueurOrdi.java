package GLescroel.myGames;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static GLescroel.myGames.Log.DEBUG_DEV;
import static GLescroel.myGames.Log.TRACE;
import static GLescroel.myGames.Tools.*;


/**
 * Classe JoueurOrdi
 * étend la classe Joueur pour instancier un joueur ordi
 */
public class JoueurOrdi extends Joueur {

    private List<String[]> tryList = new CopyOnWriteArrayList<String[]>();
    private String[] previousResult;

    //////////////// GETTER & SETTER /////////////////////////////////////
    public List<String[]> getTryList() { return tryList; }
    public void setTryList(List<String[]> tryList) { tryList = tryList; }
    public void addToTryList(String[] thisTry) { tryList.add(thisTry); }
    public void removeFromTryList(int index) { tryList.remove(index); }

    public String[] getPreviousResult() { return previousResult; }
    public void setPreviousResult(String[] previousResult) { this.previousResult = previousResult; }
    //////////////// fin GETTER & SETTER /////////////////////////////////////


    // CONSTRUCTOR
    public JoueurOrdi() {
        TRACE("joueurOrdi() (constructor)");
        setCombinaisonTrouvee(false);
    }

    /**
     * joueurChoisitCombiSecrete() = l'ordinateur définit sa combinaison secrète (random)
     * @param nbDigit nombre de caractères de la combinaison
     * @param nbValeur nombre de valeurs possibles pour chaque caractère de la combinaison
     * @return String[] combinaison définie par l'ordinateur
     */
    @Override
    protected String[] joueurChoisitCombiSecrete(int nbDigit, int nbValeur) {
        TRACE("joueurOrdi.joueurChoisitCombiSecrete()");

        String[] selectedString = new String[nbDigit];

        for(int i = 0 ; i < nbDigit; i++) {
            Random rand = new Random();
            int nbAleatoire = rand.nextInt((nbValeur-1) - 0 + 1) + 0;
            selectedString[i] = String.valueOf(nbAleatoire);
        }

        DEBUG_DEV("(ordiValue : " + convertArrayToString(selectedString) + ")");

        return selectedString;
    }

    /**
     * joueurProposeCombi() : non utilisée car implémentée spécifiquement pour chaque jeu
     * @param nomJeu nom du jeu en cours
     * @param nbDigit nombre de caractères de la combinaison
     * @param nbValeur nombre de possible pour chaque caractère de la combinaison
     * @return String[] une combinaison proposée par l'ordinateur
     */
    @Override
    protected String[] joueurProposeCombi(String nomJeu, int nbDigit, int nbValeur) {
        TRACE("joueurOrdi.joueurProposeCombi()");

        if(nomJeu.equals(Jeu.nomMasterMind))
            return ordiProposeCombiMasterMind();
        else
            return ordiProposeCombiPlusMoins(nbDigit);
    }

    /**
     * ordiProposeCombiMasterMind() permet à l'ordinateur de proposer une combinaison au jeu du MAsterMind
     * @return String[] combinaison Mastermind proposée par l'ordinateur
     */
    protected String[] ordiProposeCombiMasterMind() {
        TRACE("joueurOrdi.ordiProposeCombiMasterMind()");

        if(tryList.size() == 0)
            throw new RuntimeException("aucune possibilité restante");

        int[] minMax = {0, (tryList.size()-1)};
        //DEBUG_DEV("max : " + minMax[1] + " / min : " + minMax[0]);
        Random rand = new Random();
        int nbAleatoire = rand.nextInt(minMax[1] - minMax[0] + 1) + minMax[0];
        //DEBUG_DEV("nbAleatoire : " + nbAleatoire);

        String[] value = tryList.get(nbAleatoire);

        System.out.println("Proposition de l'ordinateur : " + convertArrayToString(value));

        return value;
}


    /**
     * ordiProposeCombiPlusMoins() permet à l'ordinateur de proposer une combinaison au jeu du PlusMoins
     * @param nbDigit = longueur de la combinaison
     * @return String[] la nouvelle proposition de type PlusMoins de l'ordinateur
     */
    protected String[] ordiProposeCombiPlusMoins(int nbDigit) {
        TRACE("joueurOrdi.ordiProposeCombiPlusMoins()");

        String[] selectedString = new String[nbDigit];

        int minMax[] = {0,9};

        for (int i = 0; i < nbDigit; i++) {
            if(tryList.size() != 0) {
                if (previousResult[i] == "=") {
                    minMax[0] = Integer.valueOf((tryList.get(tryList.size() - 1))[i]);
                    minMax[1] = Integer.valueOf((tryList.get(tryList.size() - 1))[i]);
                }
                else {
                    minMax = getMinMax(tryList, previousResult[i], i);
                }
            }

            Random rand = new Random();
            //System.out.println("max : " + minMax[1] + " / min : " + minMax[0]);
            int nbAleatoire = rand.nextInt(minMax[1] - minMax[0] + 1) + minMax[0];
            selectedString[i] = String.valueOf(nbAleatoire);
        }

        System.out.println("Proposition de l'ordinateur : " + convertArrayToString(selectedString));

        return selectedString;
    }

    /**
     * getMinMax() permet de définir les nouvelles valeurs min et max pour un caractère de la combinaison
     * @param previousTryList Liste des combinaisons précédemment proposées
     * @param resultChar résultat de l'évaluation du caractère
     * @param currentChar numéro du caractère à évaluer
     * @return int[min,max] les nouveaux min et max possibles pour ce caractère en fonction de l'évaluation précédente
     */
    private static int[] getMinMax(List<String[]> previousTryList, String resultChar, int currentChar) {
        TRACE("joueurOrdi.getMinMax()");

        int min = 0;
        int max = 9;

        String[] previousTryChar= new String[previousTryList.size()];

        for (int prev = 0; prev < previousTryList.size(); prev++)
            previousTryChar[prev] = (previousTryList.get(prev)[currentChar]);

        //DEBUG_DEV("tableau non ordonné : " + convertArrayToString(previousTryChar));

        boolean changed = false;
        do {
            changed = false;
            for (int prev = 1; prev < previousTryChar.length; prev++) {
                if ((Integer.valueOf(previousTryChar[prev])) < (Integer.valueOf(previousTryChar[prev-1]))) {
                    String inter = previousTryChar[prev];
                    previousTryChar[prev] = previousTryChar[prev-1];
                    previousTryChar[prev-1] = inter;
                    changed = true;
                }
            }
        } while(changed == true);

        //DEBUG_DEV("tableau ordonné : " + convertArrayToString(previousTryChar));

        if(resultChar == "-") {
            if(previousTryChar.length == 1) //si premier test alors min = 0 et max = valeur testée
                min = 0;
            else
            {
                if(Integer.valueOf(previousTryChar[0]) >= Integer.valueOf((previousTryList.get(previousTryList.size()-1))[currentChar]))
                    min = 0; //si valeur testée plus petite que toutes les valeurs testées, min reste à zéro et max = valeur testée
                else {
                    for(int c = 0 ; c < (Integer.valueOf(previousTryChar.length)-1); c++)
                    {
                        if(((Integer.valueOf(previousTryChar[c]) + 1) != (Integer.valueOf(previousTryChar[c+1]))) //check si l'essai supérieur est déjà testée (pour ne pas tester 2 fois la même
                                && ((Integer.valueOf(previousTryChar[c])) <= (Integer.valueOf(((previousTryList.get(previousTryList.size()-1))[currentChar]))))) //check si la valeur min n'est pas inférieure à la valeur testée sur laquelle on doit proposer plus
                        {
                            min = Integer.valueOf(previousTryChar[c]) + 1;
                            //DEBUG_DEV("break min");
                            break;
                        }
                        else
                            min = Integer.valueOf(previousTryChar[0]) + 1;
                    }
                }
            }
            max = Integer.valueOf((previousTryList.get(previousTryList.size()-1))[currentChar]) -1;
        }
        else{
            min = Integer.valueOf((previousTryList.get(previousTryList.size()-1))[currentChar])+1;
            if(previousTryChar.length == 1) // si pas de test précédent
                max = 9;
            else {
                if(Integer.valueOf(previousTryChar[previousTryChar.length - 1]) <= Integer.valueOf((previousTryList.get(previousTryList.size() - 1))[currentChar]))
                    max = 9;
                else {
                    for(int c = ((Integer.valueOf(previousTryChar.length)) -1) ; c > 0; c--)
                    {
                        if(((Integer.valueOf(previousTryChar[c]) - 1) != (Integer.valueOf(previousTryChar[c-1]))) &&
                                ((Integer.valueOf(previousTryChar[c])) >= Integer.valueOf((previousTryList.get(previousTryList.size()-1))[currentChar])))
                        {
                            max = Integer.valueOf(previousTryChar[c]) - 1;
                            //DEBUG_DEV("break max");
                            break;
                        }
                        else
                            max = Integer.valueOf(previousTryChar[previousTryChar.length-1]) - 1;
                    }
                }
            }
        }

        int[] minMax = {min, max};
        return minMax;


    }

}
