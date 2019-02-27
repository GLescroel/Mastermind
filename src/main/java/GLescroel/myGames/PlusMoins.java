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

        if(mode.equals(modeDefenseur))
            runPlusMoinsDefenseur();
        else if(mode.equals(modeChallenger))
            runPlusMoinsChallenger();
        else if(mode.equals(modeDuel)) {
            runPlusMoinsDuel();
        }

        affichageResultat(joueur, ordi, mode);
    }

    /**
     * runPlusMoinsDefenseur() exécute le jeu PlusMoins dans le mode défenseur
     */
    public void runPlusMoinsDefenseur() {

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit, 10));

        List<String[]> TryList = new CopyOnWriteArrayList<String[]>();
        String[] previousResult = new String[nbDigit];

        int essai = 0;
        do {
            TryList.add(ordi.ordiProposeCombiPlusMoins(TryList, previousResult, nbDigit));
            ordi.setCombinaisonTrouvee(evaluerPlusMoinsProposition(joueur.getCombinaisonSecrete(), TryList.get(essai), previousResult));
            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);

    }

    /**
     * runPlusMoinsChallenger() exécute le jeu PlusMoins dans le mode Challenger
     */
    public void runPlusMoinsChallenger() {

        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit, 10));

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerPlusMoinsProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit, 10), null));
            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);

    }

    /**
     * runPlusMoinsDuel() exécute le jeu PlusMoins dans le mode duel
     */
    public void runPlusMoinsDuel() {

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit, 10));
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit, 10));

        List<String[]> TryList = new CopyOnWriteArrayList<String[]>();
        String[] previousResult = new String[nbDigit];

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerPlusMoinsProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit, 10), null));

            TryList.add(ordi.ordiProposeCombiPlusMoins(TryList, previousResult, nbDigit));
            ordi.setCombinaisonTrouvee(evaluerPlusMoinsProposition(joueur.getCombinaisonSecrete(), TryList.get(essai), previousResult));

            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee() && !ordi.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);


    }

    /**
     * evaluerPlusMoinsProposition() évalue la combinaison proposée par l'ordinateur ou le joueur
     * @param secretValue du joueur ou de l'ordinateur
     * @param tryValue la proposition du joueur ou de l'ordinateur
     * @param tryResult l'évaluation à mettre à jour pour l'ordinateur
     * @return boolean qui indique si le joueur ou l'ordinateur a trouvé ou non
     */
    public static boolean evaluerPlusMoinsProposition(String[] secretValue, String[] tryValue, String[] tryResult) {

        String tryResultString = "";
        String resultatCaractere ="";
        boolean allGood = true;
        for(int i = 0 ; i < tryValue.length; i++)
        {
            if(Integer.valueOf(secretValue[i]) == Integer.valueOf(tryValue[i]))
                resultatCaractere = "=";
            else if(Integer.valueOf(secretValue[i]) > Integer.valueOf(tryValue[i]))
            {
                resultatCaractere = "+";
                allGood = false;
            }
            else
            {
                resultatCaractere = "-";
                allGood = false;
            }
            tryResultString += resultatCaractere;
            if(tryResult != null)
                tryResult[i] = resultatCaractere;
        }

        if(tryResult == null)
            System.out.println("Votre résultat : " + tryResultString);
        else
            System.out.println("Résultat de l'ordinateur    : " + tryResultString);

        return allGood;
    }

}
