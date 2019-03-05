package GLescroel.myGames;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static GLescroel.myGames.Main.MODERUN;
import static org.junit.Assert.assertEquals;

public class MastermindTest {

    @Test
    public void Given_Mastermind_When_RunDefenseur_Then_ComputerWins() {
        MODERUN = Parametres.runModeProd;
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        Mastermind myMastermind = new Mastermind(Jeu.nomMasterMind, joueur, ordi, Jeu.modeDefenseur, 4, 10, 100);
        System.setIn(new ByteArrayInputStream(String.format("0123").getBytes()));
        myMastermind.runMastermind();

        assertEquals(true, ordi.isCombinaisonTrouvee());
    }

    @Test
    public void Given_Mastermind_When_RunChallenger_Then_JoueurWins() {
        MODERUN = Parametres.runModeProd;
        Parametres param = new Parametres();
        param.readRunMode();
        JoueurHumain joueur = new JoueurHumain();
        JoueurOrdi ordi = new JoueurOrdi();
        Mastermind myMastermind = new Mastermind(Jeu.nomMasterMind, joueur, ordi, Jeu.modeChallenger, 4, 10, 100);
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(4, 6));
        System.setIn(new ByteArrayInputStream(String.format(Tools.convertArrayToString(ordi.getCombinaisonSecrete())).getBytes()));
        joueur.setCombinaisonTrouvee(myMastermind.evaluerProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(Jeu.nomMasterMind, 4, 10), null));
        assertEquals(true, joueur.isCombinaisonTrouvee());
    }

    @Test
    public void Given_Mastermind_When_CheckProposition_Then_returnEval() {
        MODERUN = Parametres.runModeProd;
        Parametres param = new Parametres();
        param.readRunMode();
        Mastermind myMastermind = new Mastermind(Jeu.nomMasterMind, null, null, Jeu.modeChallenger, 4, 10, 100);

        String[] secret  = {"0","1","2","3"};
        String[] proposition = {"3","2","1","0"};
        String[] result = new String[2];

        myMastermind.evaluerProposition(secret, proposition, result );
        assertEquals("0", result[0]);
        assertEquals("4", result[1]);

        proposition = new String[]{"0", "1", "2", "3"};
        myMastermind.evaluerProposition(secret, proposition, result );
        assertEquals("4", result[0]);
        assertEquals("0", result[1]);

        proposition = new String[]{"2", "3", "1", "0"};
        myMastermind.evaluerProposition(secret, proposition, result );
        assertEquals("0", result[0]);
        assertEquals("4", result[1]);

        proposition = new String[]{"0", "1", "5", "6"};
        myMastermind.evaluerProposition(secret, proposition, result );
        assertEquals("2", result[0]);
        assertEquals("0", result[1]);

        proposition = new String[]{"5", "7", "1", "0"};
        myMastermind.evaluerProposition(secret, proposition, result );
        assertEquals("0", result[0]);
        assertEquals("2", result[1]);

    }


}