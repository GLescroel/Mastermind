package GLescroel.myGames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * le Main()
 * lance le programme !
 */
public class Main {

    public static String MODERUN = "";
    public static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args){

        System.out.println(LogManager.ROOT_LOGGER_NAME);

//        LOGGER.debug("Debug Message Logged !!!");
//        LOGGER.info("Info Message Logged !!!");
//        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));

        /*if(args.length !=0)
        {
            for(int i = 0; i < args.length; i++)
                System.out.println(args[i]);
        }
        else
            System.out.println("arguements vides");*/

        if(args.length > 0) {
            if (args[0].equals("-DEV") || args[0].equals("-DEBUG") || args[0].equals("-PROD"))
                MODERUN = args[0].replace("-", "");
        }
        //System.out.println("MODERUN en ligne de commande ; " + MODERUN);

        Partie maPartie = new Partie();
        maPartie.startPartie();

    }
}
