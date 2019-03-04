package GLescroel.myGames;

import com.sun.org.apache.xpath.internal.operations.Plus;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static GLescroel.myGames.Main.MODERUN;
import static org.junit.Assert.*;

public class PlusMoinsTest {

    @Test
    public void Given_PlusMoins_When_RunDefenseur_Then_ComputerWins() {
        MODERUN = "PROD";
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        PlusMoins myPkusMoins = new PlusMoins("PLUSMOINS", joueur, ordi, "Defenseur", 10, 100);
        System.setIn(new ByteArrayInputStream(String.format("0123456789").getBytes()));
        myPkusMoins.runPlusMoins();

        assertEquals(true, ordi.isCombinaisonTrouvee());
    }

    @Test
    public void Given_PlusMoins_When_RunChallenger_Then_JoueurWins() {
        MODERUN = "PROD";
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        PlusMoins myPlusMoins = new PlusMoins("PLUSMOINS", joueur, ordi, "Challenger", 10, 100);
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(10, 10));
        System.setIn(new ByteArrayInputStream(String.format(Tools.convertArrayToString(ordi.getCombinaisonSecrete())).getBytes()));
        joueur.setCombinaisonTrouvee(myPlusMoins.evaluerPlusMoinsProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi("PLUSMOINS", 10, 10), null));
        assertEquals(true, joueur.isCombinaisonTrouvee());
    }

    @Test
    public void Given_PlusMoins_When_CheckProposition_Then_returnEval() {
        MODERUN = "PROD";
        Parametres param = new Parametres();
        param.readRunMode();
        PlusMoins myPlusMoins = new PlusMoins("PLUSMOINS", null, null, "Challenger", 10, 100);

        String[] secret      = {"0","1","2","3", "4", "5", "6", "7", "8", "9"};
        String[] proposition = {"0","1","2","3", "4", "5", "6", "7", "8", "9"};
        String[] result = new String[10];

        myPlusMoins.evaluerPlusMoinsProposition(secret, proposition, result );
        assertEquals("==========", Tools.convertArrayToString(result));

        proposition = new String[]{"5", "5", "5", "5", "5", "5", "5", "5", "5", "5"};
        myPlusMoins.evaluerPlusMoinsProposition(secret, proposition, result );
        assertEquals("-----=++++", Tools.convertArrayToString(result));

        proposition = new String[]{"9", "9", "9", "9", "9", "9", "9", "9", "9", "9"};
        myPlusMoins.evaluerPlusMoinsProposition(secret, proposition, result );
        assertEquals("---------=", Tools.convertArrayToString(result));

        proposition = new String[]{"9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};
        myPlusMoins.evaluerPlusMoinsProposition(secret, proposition, result );
        assertEquals("-----+++++", Tools.convertArrayToString(result));


    }



}
