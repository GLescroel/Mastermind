package GLescroel.myGames;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        getParameters();
        getListeJeuxPossibles();
        getListeModesPossibles();
        getNbDigitPossibles();
        getNbValeursPossibles();
        getNbEssaisPossibles();
        readRunMode();
    }

    /**
     * getParameters() récupère toutes les lignes du fichier paramètres
     */
    public void getParameters(){

        String filename = "config.properties";
        String filePath = "C:\\Users\\s612564\\monCodeJava\\myGames\\src\\main\\resources\\";

        Path path;
        path = Paths.get((filePath+filename));

        try {
            parametresFichier = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Problème de lecture du fichier paramètres");
            e.printStackTrace();
        }

        if(parametresFichier.size() == 0)
            System.out.println("Le fichier paramètres est vide !");
    }

    /**
     * getListeJeuxPossibles() récupère la liste des jeux
     */
    public void getListeJeuxPossibles(){

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("Game="))
                jeuxPossibles.add(parametresFichier.get(i).replace("Game=", ""));
        }

        /*if(jeuxPossibles.size()>0)
        {
            for(int j = 0 ; j < jeuxPossibles.size(); j++)
                System.out.println("jeux possible : " + jeuxPossibles.get(j));
        }*/
    }

    /**
     * getListeModesPossibles() récupère les différents modes possibles
     */
    public void getListeModesPossibles(){

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("Mode="))
                modesPossibles.add(parametresFichier.get(i).replace("Mode=", ""));
        }

        /*if(modesPossibles.size()>0)
        {
            for(int j = 0 ; j < modesPossibles.size(); j++)
                System.out.println("Modes possibles : " + modesPossibles.get(j));
        }*/
    }

    /**
     * getNbDigitPossibles() récupère le nombre de caractères min et max possibles pour jouer
     */
    public void getNbDigitPossibles(){

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("nbDIGITmin="))
                nbDigitMinPossible = Integer.valueOf(parametresFichier.get(i).replace("nbDIGITmin=", ""));
            else if(parametresFichier.get(i).startsWith("nbDIGITmax="))
                nbDigitMaxPossible = Integer.valueOf(parametresFichier.get(i).replace("nbDIGITmax=", ""));
        }

        //System.out.println("nb digit min possible : " + nbDigitMinPossible + " \\ nb digit max possible : " + nbDigitMaxPossible);
    }

    /**
     * getNbValeursPossibles() récupère le nombre de valeurs min et max possibles pour jouer
     */
    public void getNbValeursPossibles(){

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("nbValeursMin="))
                nbValeursMinPossible = Integer.valueOf(parametresFichier.get(i).replace("nbValeursMin=", ""));
            else if(parametresFichier.get(i).startsWith("nbValeursMax="))
                nbValeursMaxPossible = Integer.valueOf(parametresFichier.get(i).replace("nbValeursMax=", ""));
        }

        //System.out.println("nb valeurs min possible : " + nbValeursMinPossible + " \\ nb valeurs max possible : " + nbValeursMaxPossible);
    }

    /**
     * getNbEssaisPossibles() récupère le nombre d'essais min et max possibles pour jouer
     */
    public void getNbEssaisPossibles(){

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("nbEssaisMin="))
                nbEssaisMinPossible = Integer.valueOf(parametresFichier.get(i).replace("nbEssaisMin=", ""));
            else if(parametresFichier.get(i).startsWith("nbEssaisMax="))
                nbEssaisMaxPossible = Integer.valueOf(parametresFichier.get(i).replace("nbEssaisMax=", ""));
        }

        //System.out.println("nb essais min possible : " + nbEssaisMinPossible + " \\ nb essais max possible : " + nbEssaisMaxPossible);
    }

    /**
     * getRunMode() récupère le mode d'exécution du jeu (dev, debug, prod)
     */
    public void readRunMode(){

        if(parametresFichier.size() == 0)
            return;

        for(int i = 0 ; i < parametresFichier.size() ; i++)
        {
            if(parametresFichier.get(i).startsWith("ModeRun="))
                runMode = parametresFichier.get(i).replace("ModeRun=", "");
        }

        if(!Main.MODERUN.isEmpty()) {
            runMode = Main.MODERUN;
            return;
        }

        //System.out.println("RunMode : " + runMode);
    }


}
