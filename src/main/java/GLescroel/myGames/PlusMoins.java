package GLescroel.myGames;

import java.util.ArrayList;
import java.util.List;

public class PlusMoins extends Jeu {

    String mode;
    int nbDigit;
    int nbEssai;

    JoueurOrdi ordi;
    JoueurHumain joueur;


    public PlusMoins(JoueurHumain joueur, JoueurOrdi ordi, String mode, int nbDigit, int nbEssai) {
        this.mode = mode;
        this.nbDigit = nbDigit;
        this.nbEssai = nbEssai;
        this.joueur = joueur;
        this.ordi = ordi;
    }

    public void runPlusMoins() {
        System.out.println("TODO");
    }


}
