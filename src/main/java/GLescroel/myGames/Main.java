package GLescroel.myGames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static GLescroel.myGames.Parametres.runModeDebug;

/**
 * le Main()
 * lance le programme !
 */
public class Main {

    public static String MODERUN = "";
    public static final Logger myLogger = LogManager.getLogger(GLescroel.myGames.Main.class.getName());

    public static void main(String[] args){
        myLogger.trace("----------Main()----------");

        if(args.length > 0) {
            if (args[0].equals("-DEV") || args[0].equals("-DEBUG") || args[0].equals("-PROD"))
                MODERUN = args[0].replace("-", "");

            if(MODERUN.equals(runModeDebug))
                myLogger.debug("MODERUN en ligne de commande = " + MODERUN);
        }
        //System.out.println("MODERUN en ligne de commande ; " + MODERUN);

        Partie maPartie = new Partie();
        maPartie.startPartie();

    }
}
