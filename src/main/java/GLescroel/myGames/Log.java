package GLescroel.myGames;

import static GLescroel.myGames.Main.MODERUN;
import static GLescroel.myGames.Main.myLogger;
import static GLescroel.myGames.Parametres.runModeDebug;
import static GLescroel.myGames.Parametres.runModeDev;
import static GLescroel.myGames.Partie.myDevLogger;

/**
 * Classe Log pour l'enregistrement des logs et debug avec Log4J
 */
public class Log {

    /**
     * TRACE() enregistre une trace si mode DEBUG
     * @param trace = la trace à enregistrer
     */
    public static void TRACE(String trace){
        if(Parametres.getRunMode().equals(runModeDebug) ||
                (MODERUN.equals(runModeDebug)) ||
                (MODERUN.isEmpty() && Parametres.getRunMode().isEmpty())){
            myLogger.trace("----------Tools.TRACE()----------");
            myLogger.trace("----------" + trace + "----------");
        }
    }

    /**
     * DEBUG() enregistre le debug si mode DEBUG
     * @param debug = le debug à lggger
     */
    public static void DEBUG(String debug){
        if(Parametres.getRunMode().equals(runModeDebug) ||
                (MODERUN.equals(runModeDebug)) ||
                (MODERUN.isEmpty() && Parametres.getRunMode().isEmpty())){
            myLogger.trace("----------Tools.DEBUG()----------");
            myLogger.debug(debug);
        }
    }

    /**
     * DEBUG_DEV() enregistre le debug si mode DEBUG
     * @param debug_affiche = le debug à lggger
     */
    public static void DEBUG_DEV(String debug_affiche){
        TRACE("Tools.DEBUG()");
        if(Parametres.getRunMode().equals(runModeDev)) {
            myDevLogger.debug(debug_affiche);
        }
    }

}
