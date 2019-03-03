
package GLescroel.myGames;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static GLescroel.myGames.Log.DEBUG_DEV;
import static GLescroel.myGames.Log.TRACE;


/**
 * Classe Interaction
 * contient des fonctions "outils" pour gérer les interactions avec l'utilisateur, notamment ses saisies attendues
 */
public class Interaction {

    /**
     * ask someThing()
     * @param category =  catégorie de la demande (jeu, mode)
     * @param responses = la liste des réponses possibles
     * @return le numéro de la réponse dans la liste
     */
    public static int askSomething(String category, String[] responses) {
            TRACE("Interaction.askSomething()");

        System.out.println("Choix du " + category);

        for (int i = 1; i <= responses.length; i++)
            System.out.println(i + " - " + responses[i - 1]);

        System.out.println("Que souhaitez vous comme " + category + "?");

        int responseNb=0;
        boolean responseIsGood = false;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                responseNb = sc.nextInt();
                responseIsGood = (responseNb >= 1 && responseNb <= responses.length);
            }catch (InputMismatchException exception){
                System.out.println("Erreur de saisie");
                sc.next();
                responseIsGood = false;}

            /**if (nbResponse >= 1 && nbResponse <= responses.length)
             responseIsGood = true;
             else responseIsGood = false;*/

            if (responseIsGood == true) {
                DEBUG_DEV("Vous avez choisi comme " + category + " : " + responses[responseNb - 1]);
            }
            else {
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel)
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                else
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
            }
        } while (responseIsGood == false);

        return responseNb;
    }


    /**
     * Permet de récupérer le nombre saisi par le joueur pour une donnée
     * @param  requestedValue (nbCar ou nbTry), int minValue, int maxValue
     * @param minValue valeur minimale de la proposition
     * @param maxValue valeur maximale de la proposition
     * @return le nombre saisi par le joueur
     */
    public static int askQuantity(String requestedValue, int minValue, int maxValue) {
            TRACE("Interaction.askQuantity()");

        System.out.println("Choix du nombre " + requestedValue + " entre " + minValue + " et " + maxValue);
        System.out.println("Avec combien " + requestedValue + " souhaitez vous jouer ?");

        int responseNb=0;
        boolean responseIsGood = false;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                responseNb = sc.nextInt();
                responseIsGood = (responseNb >= minValue && responseNb <= maxValue);
            }catch (InputMismatchException exception){
                System.out.println("Erreur de saisie");
                sc.next();
                responseIsGood = false;}

            /**if (nbResponse >= 1 && nbResponse <= responses.length)
             responseIsGood = true;
             else responseIsGood = false;*/

            if (responseIsGood == true)
                DEBUG_DEV("Vous avez choisi comme quantité " + requestedValue + " : " + responseNb);
            else
                System.out.println("Vous n'avez pas choisi le nombre " + requestedValue + " parmi les choix proposés");

        } while (responseIsGood == false);

        return responseNb;
    }

    /**
     * askYesNo() = demande à l'utilisateur une réponse par O ou N à une question
     * @param question = question posée
     * @return String de la réponse
     */
    public static String askYesNo(String question) {
            TRACE("Interaction.askYesNo()");

        System.out.println(question);

        String response="";
        boolean responseIsGood = false;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                response = sc.next();
                responseIsGood = (response.toUpperCase().equals("O") || response.toUpperCase().equals("N"));
            }catch (InputMismatchException exception){
                System.out.println("Erreur de saisie");
                sc.next();
                responseIsGood = false;}

        } while (responseIsGood == false);

        return response.toUpperCase();
    }

    /**
     * askCombinaison() demande au joueur de saisir une combinaison numérique de longueur nbDigit
     * @param nbDigit nombre de caractères de la combinaison
     * @param nbValeur nombre de valeurs possibles pour chaque caractère de la combinaison
     * @return String[] la combinaison saisie par le joueur
     * @see Joueur#joueurProposeCombi
     */
    public static String[] askCombinaison(int nbDigit, int nbValeur){
            TRACE("Interaction.askCombinaison()");

        String[] enteredString = new String[nbDigit];

        System.out.println("Saisissez une combinaison de " + nbDigit + " chiffres compris entre 0 et " + (nbValeur-1) + " :" );

        String responseNb="";
        boolean responseIsGood = false;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                responseNb = sc.next();

                // compilation de la regex
                Pattern p = Pattern.compile("[0-"+(nbValeur-1)+"]{"+ nbDigit+"}");
                // création d'un moteur de recherche
                Matcher m = p.matcher(responseNb);
                // lancement de la recherche de toutes les occurrences
                responseIsGood = m.matches();
                // si recherche fructueuse
                if(responseIsGood) {
                    // pour chaque groupe (ici un seul possible car même longueur)
                    for(int i=0; i <= m.groupCount(); i++) {
                        // affichage de la sous-chaîne captu1rée
                        DEBUG_DEV("Votre saisie   : " + m.group(i));
                    }
                    for(int i=0; i <= (responseNb.length()-1); i++)
                        enteredString[i] = String.valueOf(responseNb.charAt(i));
                }else System.out.println("Votre saisie est incorrecte");

            }catch (InputMismatchException exception){
                System.out.println("Erreur de saisie");
                sc.next();
                responseIsGood = false;}

        } while (responseIsGood == false);

        return enteredString;

    }

}
