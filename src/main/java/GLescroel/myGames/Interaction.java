package GLescroel.myGames;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {

    public static int askSomething(String category, String[] responses) {

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

            if (responseIsGood == true)
                System.out.println("Vous avez choisi comme " + category + " : " + responses[responseNb - 1]);
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
     * @param String requestedValue (nbCar ou nbTry), int minValue, int maxValue
     * @return le nombre saisi par le joueur
     */
    public static int askQuantity(String requestedValue, int minValue, int maxValue) {

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
                System.out.println("Vous avez choisi comme quantité " + requestedValue + " : " + responseNb);
            else
                System.out.println("Vous n'avez pas choisi le nombre " + requestedValue + " parmi les choix proposés");
        } while (responseIsGood == false);

        return responseNb;
    }

    public static String askText(String category) {

        System.out.println("Choix du " + category);

        String response="";
        boolean responseIsGood = false;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                response = sc.next();
                responseIsGood = (response.length() > 0);
            }catch (InputMismatchException exception){
                System.out.println("Erreur de saisie");
                sc.next();
                responseIsGood = false;}

            if (responseIsGood == true)
                System.out.println("Vous avez choisi comme " + category + " : " + response);
            else {
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel)
                    System.out.println("Vous n'avez pas choisi d'" + category);
                else
                    System.out.println("Vous n'avez pas choisi de " + category);
            }
        } while (responseIsGood == false);

        return response;
    }


}
