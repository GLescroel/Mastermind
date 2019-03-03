package GLescroel.myGames;

import static GLescroel.myGames.Log.TRACE;

public class Tools {

    /**
     * convertArrayToString convertit un String[] en String
     * @param toConvert la valeur String[] à convertir en String
     * @return String converted = la valeur convertie en String
     */
    public static String convertArrayToString(String[] toConvert){
        TRACE("Tools.convertArrayToString()");

        String converted = "";
        for(int c = 0 ; c < toConvert.length ; c++)
            converted+= toConvert[c];
        return converted;
    }

}
