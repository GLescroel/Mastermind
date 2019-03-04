package GLescroel.myGames;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static GLescroel.myGames.Main.MODERUN;
import static org.junit.Assert.assertEquals;

public class MastermindTest {

    @Test
    public void Given_Mastermind_When_RunDefenseur_Then_ComputerWins() {
        MODERUN = "PROD";
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        Mastermind myMastermind = new Mastermind("MASTERMIND", joueur, ordi, "Defenseur", 4, 10, 100);
        System.setIn(new ByteArrayInputStream(String.format("0123").getBytes()));
        myMastermind.runMastermind();

        assertEquals(true, ordi.isCombinaisonTrouvee());
    }

    @Test
    public void Given_Mastermind_When_RunChallenger_Then_JoueurWins() {
        MODERUN = "PROD";
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        Mastermind myMastermind = new Mastermind("MASTERMIND", joueur, ordi, "Challenger", 4, 10, 100);
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(4, 6));
        System.setIn(new ByteArrayInputStream(String.format(Tools.convertArrayToString(ordi.getCombinaisonSecrete())).getBytes()));
        joueur.setCombinaisonTrouvee(myMastermind.evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi("MASTERMIND", 4, 10), null));
        assertEquals(true, joueur.isCombinaisonTrouvee());
    }

    @Test
    public void Given_Mastermind_When_CheckProposition_Then_returnEval() {
        MODERUN = "PROD";
        Parametres param = new Parametres();
        param.readRunMode();
        Mastermind myMastermind = new Mastermind("MASTERMIND", null, null, "Challenger", 4, 10, 100);

        String[] secret  = {"0","1","2","3"};
        String[] proposition = {"3","2","1","0"};
        String[] result = new String[2];

        myMastermind.evaluerMasterMindProposition(secret, proposition, result );
        assertEquals("0", result[0]);
        assertEquals("4", result[1]);

        proposition = new String[]{"0", "1", "2", "3"};
        myMastermind.evaluerMasterMindProposition(secret, proposition, result );
        assertEquals("4", result[0]);
        assertEquals("0", result[1]);

        proposition = new String[]{"2", "3", "1", "0"};
        myMastermind.evaluerMasterMindProposition(secret, proposition, result );
        assertEquals("0", result[0]);
        assertEquals("4", result[1]);

        proposition = new String[]{"0", "1", "5", "6"};
        myMastermind.evaluerMasterMindProposition(secret, proposition, result );
        assertEquals("2", result[0]);
        assertEquals("0", result[1]);

        proposition = new String[]{"5", "7", "1", "0"};
        myMastermind.evaluerMasterMindProposition(secret, proposition, result );
        assertEquals("0", result[0]);
        assertEquals("2", result[1]);

    }


}