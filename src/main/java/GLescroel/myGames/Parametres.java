package GLescroel.myGames;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static GLescroel.myGames.Log.TRACE;
import static GLescroel.myGames.Main.MODERUN;
import static GLescroel.myGames.Main.myLogger;

/**
 * Classe Parametres permet la récupération des paramètres de jeu dans le fichier de paramètres
 */
public class Parametres {

    private List<String> parametresFichier = new ArrayList<String>();
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

        String filename = "config.properties";
        String filepath = "src\\main\\resources\\";

        Path path;
        path = Paths.get(filepath+filename);

        try {
            parametresFichier = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Problème de lecture du fichier paramètres");
            e.printStackTrace();
        }

        if(parametresFichier.size() == 0)
            throw new IllegalStateException("Le fichier paramètres est vide !");
    }

    /**
     * getListeJeuxPossibles() récupère la liste des jeux
     */
    public void getListeJeuxPossibles(){
        TRACE("Parametres.getListeJeuxPossibles()");

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("Game="))
                jeuxPossibles.add(parametresFichier.get(i).replace("Game=", ""));
        }
    }

    /**
     * getListeModesPossibles() récupère les différents modes possibles
     */
    public void getListeModesPossibles(){
        TRACE("Parametres.getListeModesPossibles()");

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("Mode="))
                modesPossibles.add(parametresFichier.get(i).replace("Mode=", ""));
        }

    }

    /**
     * getNbDigitPossibles() récupère le nombre de caractères min et max possibles pour jouer
     */
    public void getNbDigitPossibles(){
        TRACE("Parametres.getNbDigitPossibles()");

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("nbDIGITmin="))
                nbDigitMinPossible = Integer.valueOf(parametresFichier.get(i).replace("nbDIGITmin=", ""));
            else if(parametresFichier.get(i).startsWith("nbDIGITmax="))
                nbDigitMaxPossible = Integer.valueOf(parametresFichier.get(i).replace("nbDIGITmax=", ""));
        }

    }

    /**
     * getNbValeursPossibles() récupère le nombre de valeurs min et max possibles pour jouer
     */
    public void getNbValeursPossibles(){
         TRACE("Parametres.getNbValeursPossibles()");

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("nbValeursMin="))
                nbValeursMinPossible = Integer.valueOf(parametresFichier.get(i).replace("nbValeursMin=", ""));
            else if(parametresFichier.get(i).startsWith("nbValeursMax="))
                nbValeursMaxPossible = Integer.valueOf(parametresFichier.get(i).replace("nbValeursMax=", ""));
        }

    }

    /**
     * getNbEssaisPossibles() récupère le nombre d'essais min et max possibles pour jouer
     */
    public void getNbEssaisPossibles(){
        TRACE("Parametres.getNbEssaisPossibles()");

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("nbEssaisMin="))
                nbEssaisMinPossible = Integer.valueOf(parametresFichier.get(i).replace("nbEssaisMin=", ""));
            else if(parametresFichier.get(i).startsWith("nbEssaisMax="))
                nbEssaisMaxPossible = Integer.valueOf(parametresFichier.get(i).replace("nbEssaisMax=", ""));
        }

    }

    /**
     * getRunMode() récupère le mode d'exécution du jeu (dev, debug, prod)
     */
    public void readRunMode(){
        if(MODERUN.equals(runModeDebug))
            myLogger.trace("----------Parametres.getRunMode()----------");

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("ModeRun="))
                runMode = parametresFichier.get(i).replace("ModeRun=", "");
        }

        if(!MODERUN.isEmpty()) {
            runMode = MODERUN;
            return;
        }

    }


}
