package GLescroel.myGames;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Classe PlusMoins étend la classe abstraite Jeu
 */
public class PlusMoins extends Jeu {

    /**
     * PlusMoins() constructor de la classe PlusMoins
     * @param nomJeu nom du jeu
     * @param joueur joueur humain
     * @param ordi joueur ordinateur
     * @param mode mode de jeu
     * @param nbDigit nombre de caractères des combinaisons
     * @param nbEssai nombre d'essais max pour trouver la solution
     */
    //// Constructor
    public PlusMoins (String nomJeu, JoueurHumain joueur, JoueurOrdi ordi, String mode, int nbDigit, int nbEssai) {
        super(nomJeu, joueur, ordi, mode, nbDigit, nbEssai);
    }

    /**
     * runPlusMoins() lance le jeu PlusMoins dans le mode choisi
     */
    public void runPlusMoins(){

        if(mode.equals("Defenseur"))
            runPlusMoinsDefenseur();
        else if(mode.equals("Challenger"))
            runPlusMoinsChallenger();
        else if(mode.equals("Duel")) {
            runPlusMoinsDuel();
        }

        affichageResultat(joueur, ordi, mode);
    }

    /**
     * runPlusMoinsDefenseur() exécute le jeu PlusMoins dans le mode défenseur
     */
    public void runPlusMoinsDefenseur() {

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit));

        List<String[]> TryList = new CopyOnWriteArrayList<String[]>();
        String[] previousResult = new String[nbDigit];

        int essai = 0;
        do {
            TryList.add(ordi.ordiProposeCombiPlusMoins(TryList, previousResult, nbDigit));
            ordi.setCombinaisonTrouvee(evaluerPlusMoinsPropositionOrdi(joueur.getCombinaisonSecrete(), TryList.get(essai), previousResult));
            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);

    }

    /**
     * runPlusMoinsChallenger() exécute le jeu PlusMoins dans le mode Challenger
     */
    public void runPlusMoinsChallenger() {

        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit));

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerPlusMoinsPropositionJoueur(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit)));
            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);

    }

    /**
     * runPlusMoinsDuel() exécute le jeu PlusMoins dans le mode duel
     */
    public void runPlusMoinsDuel() {

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit));
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit));

        List<String[]> TryList = new CopyOnWriteArrayList<String[]>();
        String[] previousResult = new String[nbDigit];

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerPlusMoinsPropositionJoueur(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit)));

            TryList.add(ordi.ordiProposeCombiPlusMoins(TryList, previousResult, nbDigit));
            ordi.setCombinaisonTrouvee(evaluerPlusMoinsPropositionOrdi(joueur.getCombinaisonSecrete(), TryList.get(essai), previousResult));

            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee() && !ordi.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);


    }

    /**
     * evaluerPlusMoinsPropositionJoueur() évalue la combinaison proposée par le joueur
     * @param secretValue de l'ordinateur
     * @param tryValue la proposition du joueur
     * @return boolean indiquant si le joueur a trouvé ou non
     */
    public static boolean evaluerPlusMoinsPropositionJoueur(String[] secretValue, String[] tryValue) {

        String tryResult = "";
        boolean allGood = true;
        for(int i = 0 ; i < tryValue.length; i++)
        {
            if(Integer.valueOf(secretValue[i]/*.toString()*/) == Integer.valueOf(tryValue[i]/*.toString()*/))
                tryResult += "=";
            else if(Integer.valueOf(secretValue[i]/*.toString()*/) > Integer.valueOf(tryValue[i]/*.toString()*/))
            {
                tryResult += "+";
                allGood = false;
            }
            else
            {
                tryResult += "-";
                allGood = false;
            }
        }

        System.out.println("Votre résultat : " + tryResult);

        return allGood;
    }

    /**
     * evaluerPlusMoinsPropositionOrdi() évalue la combinaison proposée par l'ordinateur
     * @param secretValue du joueur
     * @param tryValue la proposition de l'ordinateur
     * @param tryResult l'évaluation à mettre à jour
     * @return boolean qui indique si l'ordinateur a trouvé ou non
     */
    public static boolean evaluerPlusMoinsPropositionOrdi(String[] secretValue, String[] tryValue, String[] tryResult) {

        /*String aff = "";
        for(int i = 0; i < (tryValue.length); i++)
            aff += tryValue[i];
        System.out.println("test : " + aff + " length : " + (tryValue.length));*/

        boolean allGood = true;
        for(int i = 0 ; i < tryValue.length; i++)
        {
            if(Integer.valueOf(secretValue[i].toString()) == Integer.valueOf(tryValue[i].toString()))
                tryResult[i] = "=";
            else if(Integer.valueOf(secretValue[i].toString()) > Integer.valueOf(tryValue[i].toString()))
            {
                tryResult[i] = "+";
                allGood = false;
            }
            else
            {
                tryResult[i] = "-";
                allGood = false;
            }
        }

        String affichage = "";
        for(int i = 0; i < tryResult.length; i++)
            affichage += tryResult[i];
        System.out.println("Résultat  : " + affichage);

        return allGood;
    }



}
