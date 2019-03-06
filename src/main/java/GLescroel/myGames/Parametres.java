package GLescroel.myGames;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import static GLescroel.myGames.Log.TRACE;
import static GLescroel.myGames.Main.MODERUN;
import static GLescroel.myGames.Main.myLogger;

/**
 * Classe Parametres permet la récupération des paramètres de jeu dans le fichier de paramètres
 */
public class Parametres {

    private Properties myProperties = new Properties();

    private List<String> jeuxPossibles = new ArrayList<String>();
    private List<String> modesPossibles = new ArrayList<String>();
    private int nbDigitMinPossible;
    private int nbDigitMaxPossible;
    private int nbValeursMinPossible;
    private int nbValeursMaxPossible;
    private int nbEssaisMinPossible;
    private int nbEssaisMaxPossible;
    private static String runMode;

    public static final String runModeDebug = "DEBUG";
    public static final String runModeDev = "DEV";
    public static final String runModeProd = "PROD";

    //////////// GETTER & SETTER /////////////////////////////////////
    public List<String> getJeuxPossibles() { return jeuxPossibles; }
    public List<String> getModesPossibles() { return modesPossibles; }
    public int getNbDigitMinPossible() { return nbDigitMinPossible; }
    public int getNbDigitMaxPossible() { return nbDigitMaxPossible; }
    public int getNbValeursMinPossible() { return nbValeursMinPossible; }
    public int getNbValeursMaxPossible() { return nbValeursMaxPossible; }
    public int getNbEssaisMinPossible() { return nbEssaisMinPossible; }
    public int getNbEssaisMaxPossible() { return nbEssaisMaxPossible; }
    public static String getRunMode() { return runMode; }
    //////////// fin GETTER & SETTER /////////////////////////////////////

    /**
     * runInit() lance la récupération des différents paramètres
     *@see Parametres#getParameters
     *@see Parametres#getListeJeuxPossibles
     *@see Parametres#getListeModesPossibles
     *@see Parametres#getNbDigitPossibles
     *@see Parametres#getNbEssaisPossibles
     *@see Parametres#getRunMode
     */
    public void runInit(){
        if(MODERUN.equals(runModeDebug))
            myLogger.trace("----------Parametres.runInit()----------");

        getParameters();
        readRunMode();
        getListeJeuxPossibles();
        getListeModesPossibles();
        getNbDigitPossibles();
        getNbValeursPossibles();
        getNbEssaisPossibles();
    }

    /**
     * getParameters() récupère toutes les lignes du fichier paramètres
     */
    public void getParameters() {
        if(MODERUN.equals(runModeDebug))
            myLogger.trace("----------Parametres.getParameters()----------");

        InputStream myConfigFile = null;
        ClassLoader Loader = Thread.currentThread().getContextClassLoader();
        try{
            myConfigFile = Loader.getResourceAsStream ("config.properties");
            myProperties.load(myConfigFile);
        }catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (myConfigFile != null) {
                try {
                    myConfigFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * getListeJeuxPossibles() récupère la liste des jeux
     */
    public void getListeJeuxPossibles(){
        TRACE("Parametres.getListeJeuxPossibles()");

        Enumeration<?> myPropertiesContaint = myProperties.keys();
        while (myPropertiesContaint.hasMoreElements()) {
            String cle = (String) myPropertiesContaint.nextElement();
            if(cle.contains("Jeu"))
            jeuxPossibles.add(myProperties.getProperty(cle));
        }
    }

    /**
     * getListeModesPossibles() récupère les différents modes possibles
     */
    public void getListeModesPossibles(){
        TRACE("Parametres.getListeModesPossibles()");

        Enumeration<?> myPropertiesContaint = myProperties.keys();
        while (myPropertiesContaint.hasMoreElements()) {
            String cle = (String) myPropertiesContaint.nextElement();
            if(cle.startsWith("Mode"))
                modesPossibles.add(myProperties.getProperty(cle));
        }
    }

    /**
     * getNbDigitPossibles() récupère le nombre de caractères min et max possibles pour jouer
     */
    public void getNbDigitPossibles(){
        TRACE("Parametres.getNbDigitPossibles()");

        nbDigitMinPossible = Integer.valueOf(myProperties.getProperty("nbDIGITmin"));
        nbDigitMaxPossible = Integer.valueOf(myProperties.getProperty("nbDIGITmax"));

    }

    /**
     * getNbValeursPossibles() récupère le nombre de valeurs min et max possibles pour jouer
     */
    public void getNbValeursPossibles(){
         TRACE("Parametres.getNbValeursPossibles()");

        nbValeursMinPossible = Integer.valueOf(myProperties.getProperty("nbValeursMin"));
        nbValeursMaxPossible = Integer.valueOf(myProperties.getProperty("nbValeursMax"));

    }

    /**
     * getNbEssaisPossibles() récupère le nombre d'essais min et max possibles pour jouer
     */
    public void getNbEssaisPossibles(){
        TRACE("Parametres.getNbEssaisPossibles()");

        nbEssaisMinPossible = Integer.valueOf(myProperties.getProperty("nbEssaisMin"));
        nbEssaisMaxPossible = Integer.valueOf(myProperties.getProperty("nbEssaisMax"));

    }

    /**
     * getRunMode() récupère le mode d'exécution du jeu (dev, debug, prod)
     */
    public void readRunMode(){
        if(MODERUN.equals(runModeDebug))
            myLogger.trace("----------Parametres.getRunMode()----------");

        runMode = myProperties.getProperty("runMode");

        if(!MODERUN.isEmpty())
            runMode = MODERUN;

    }


}
