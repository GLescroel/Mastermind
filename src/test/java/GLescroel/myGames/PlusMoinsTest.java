package GLescroel.myGames;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static GLescroel.myGames.Main.MODERUN;
import static org.junit.Assert.*;

public class PlusMoinsTest {

    @Test
    public void Given_PlusMoins_When_RunDefenseur_Then_ComputerWins() {
        MODERUN = Parametres.runModeProd;
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        PlusMoins myPkusMoins = new PlusMoins(Jeu.nomPlusMoins, joueur, ordi, Jeu.modeDefenseur, 10, 100);
        System.setIn(new ByteArrayInputStream(String.format("0123456789").getBytes()));
        myPkusMoins.runPlusMoins();

        assertEquals(true, ordi.isCombinaisonTrouvee());
    }

    @Test
    public void Given_PlusMoins_When_RunChallenger_Then_JoueurWins() {
        MODERUN = Parametres.runModeProd;
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        PlusMoins myPlusMoins = new PlusMoins(Jeu.nomPlusMoins, joueur, ordi, Jeu.modeChallenger, 10, 100);
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(10, 10));
        System.setIn(new ByteArrayInputStream(String.format(Tools.convertArrayToString(ordi.getCombinaisonSecrete())).getBytes()));
        joueur.setCombinaisonTrouvee(myPlusMoins.evaluerProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(Jeu.nomPlusMoins, 10, 10), null));
        assertEquals(true, joueur.isCombinaisonTrouvee());
    }

    @Test
    public void Given_PlusMoins_When_CheckProposition_Then_returnEval() {
        MODERUN = Parametres.runModeProd;
        Parametres param = new Parametres();
        param.readRunMode();
        PlusMoins myPlusMoins = new PlusMoins(Jeu.nomPlusMoins, null, null, Jeu.modeChallenger, 10, 100);

        String[] secret      = {"0","1","2","3", "4", "5", "6", "7", "8", "9"};
        String[] proposition = {"0","1","2","3", "4", "5", "6", "7", "8", "9"};
        String[] result = new String[10];

        myPlusMoins.evaluerProposition(secret, proposition, result );
        assertEquals("==========", Tools.convertArrayToString(result));

        proposition = new String[]{"5", "5", "5", "5", "5", "5", "5", "5", "5", "5"};
        myPlusMoins.evaluerProposition(secret, proposition, result );
        assertEquals("-----=++++", Tools.convertArrayToString(result));

        proposition = new String[]{"9", "9", "9", "9", "9", "9", "9", "9", "9", "9"};
        myPlusMoins.evaluerProposition(secret, proposition, result );
        assertEquals("---------=", Tools.convertArrayToString(result));

        proposition = new String[]{"9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};
        myPlusMoins.evaluerProposition(secret, proposition, result );
        assertEquals("-----+++++", Tools.convertArrayToString(result));


    }



}
