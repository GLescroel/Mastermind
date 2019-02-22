package GLescroel.myGames;

import java.util.List;
import java.util.Random;

public class JoueurOrdi extends Joueur {

    // CONSTRUCTOR
    public JoueurOrdi(String nomJoueur) {
        this.setEstHumain(false);
        this.setNomJoueur(nomJoueur);
        setCombinaisonTrouvee(false);
    }

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

    @Override
    protected String[] joueurProposeCombi(int nbDigit) {
        return new String[0];
    }

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
}
