package GLescroel.myGames;

import java.util.List;
import java.util.Random;

/**
 * Classe JoueurOrdi
 * étend la classe Joueur pour instancier un joueur ordi
 */
public class JoueurOrdi extends Joueur {

    // CONSTRUCTOR
    public JoueurOrdi(String nomJoueur) {
        this.setEstHumain(false);
        this.setNomJoueur(nomJoueur);
        setCombinaisonTrouvee(false);
    }

    /**
     * joueurChoisitCombiSecrete() = l'ordinateur définit sa combinaison secrète (random)
     * @param nbDigit nombre de caractères de la combinaison
     * @return String[] combinaison définie par l'ordinateur
     */
    @Override
    protected String[] joueurChoisitCombiSecrete(int nbDigit) {
        String[] selectedString = new String[nbDigit];

        for(int i = 0 ; i < nbDigit; i++) {
            Random rand = new Random();
            int nbAleatoire = rand.nextInt(9 - 0 + 1) + 0;
            selectedString[i] = String.valueOf(nbAleatoire);
        }

        String affichage = "";
        for(int i = 0; i < nbDigit; i++)
            affichage += selectedString[i];
        System.out.println("(ordiValue : " + affichage + ")");

        return selectedString;
    }

    /**
     * joueurProposeCombi() : non utilisée car implémentée spécifiquement pour chaque jeu
     * @param nbDigit nombre de caractères de la combinaison
     * @return String[] une combinaison proposée par l'ordinateur
     */
    @Override
    protected String[] joueurProposeCombi(int nbDigit) {
        return new String[0];
    }

    /**
     * ordiProposeCombiMasterMind() permet à l'ordinateur de proposer une combinaison au jeu du MAsterMind
     * @param possibleTryList liste des combinaisons encore possibles
     * @return String[] combinaison Mastermind proposée par l'ordinateur
     */
    protected String[] ordiProposeCombiMasterMind(List<String[]> possibleTryList) {

        int[] minMax = {0, (possibleTryList.size()-1)};
        Random rand = new Random();
        //System.out.println("max : " + minMax[1] + " / min : " + minMax[0]);
        int nbAleatoire = rand.nextInt(minMax[1] - minMax[0] + 1) + minMax[0];

        String[] value = possibleTryList.get(nbAleatoire);

        String affichage = "";
        for(int i = 0 ; i < value.length ; i++)
            affichage += value[i];
        System.out.println("Proposition ordi : " + affichage);

        return value;
    }


    /**
     * ordiProposeCombiPlusMoins() permet à l'ordinateur de proposer une combinaison au jeu du PlusMoins
     * @param previousTry = la liste des combinaisons précédemment proposées
     * @param previousResult = le résultat de l'évaluation de la proposition précédente
     * @param nbDigit = longueur de la combinaison
     * @return String[] la nouvelle proposition de type PlusMoins de l'ordinateur
     */
    protected String[] ordiProposeCombiPlusMoins(List<String[]> previousTry, String[] previousResult, int nbDigit) {

        String[] selectedString = new String[nbDigit];

        int minMax[] = {0,9};

        for (int i = 0; i < nbDigit; i++) {
            if(previousTry.size() != 0) {
                if (previousResult[i] == "=") {
                    //selectedString[i] = (previousTry.get(previousTry.size()-1))[i];
                    minMax[0] = Integer.valueOf((previousTry.get(previousTry.size() - 1))[i]);
                    minMax[1] = Integer.valueOf((previousTry.get(previousTry.size() - 1))[i]);
                }
                else {
                    minMax = getMinMax(previousTry, previousResult[i], i);
                }
            }

            Random rand = new Random();
            //System.out.println("max : " + minMax[1] + " / min : " + minMax[0]);
            int nbAleatoire = rand.nextInt(minMax[1] - minMax[0] + 1) + minMax[0];
            selectedString[i] = String.valueOf(nbAleatoire);
        }

        String affichage = "";
        for(int i = 0; i < nbDigit; i++)
            affichage += selectedString[i];
        System.out.println("ordiValue : " + affichage);

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
        int min = 0;
        int max = 9;

        String[] previousTryChar= new String[previousTryList.size()];

        for (int prev = 0; prev < previousTryList.size(); prev++)
            previousTryChar[prev] = (previousTryList.get(prev)[currentChar]);

        /*String a = "";
        for(int z = 0; z < previousTryChar.length; z++)
            a += previousTryChar[z];
        System.out.println("tableau NON ordonné : " + a);*/

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

        /*a = "";
        for(int z = 0; z < previousTryChar.length; z++)
            a += previousTryChar[z];
        System.out.println("tableau ordonné : " + a);*/

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
                            //System.out.println("break min");
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
                            //System.out.println("break max");
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
