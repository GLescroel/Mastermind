package GLescroel.myGames;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoueurHumain extends Joueur {

    // CONSTRUCTOR
    public JoueurHumain(String nomJoueur) {
        this.setEstHumain(true);
        this.setNomJoueur(nomJoueur);
        setCombinaisonTrouvee(false);
    }

    @Override
    protected String[] joueurChoisitCombiSecrete(int nbDigit){
        System.out.println("Choisissez votre combinaison secrète : ");
        return joueurProposeCombi(nbDigit);
    }


    @Override
    protected String[] joueurProposeCombi(int nbDigit){
        String[] enteredString = new String[nbDigit];

        System.out.println("Saisissez une combinaison de " + nbDigit + " chiffres : " );

        String responseNb="";
        boolean responseIsGood = false;
        do {
            Scanner sc = new Scanner(System.in);
            try {
                responseNb = sc.next();

                // compilation de la regex
                Pattern p = Pattern.compile("[0-9]{"+ nbDigit+"}");
                // création d'un moteur de recherche
                Matcher m = p.matcher(responseNb);
                // lancement de la recherche de toutes les occurrences
                responseIsGood = m.matches();
                // si recherche fructueuse
                if(responseIsGood) {
                    // pour chaque groupe (ici un seul possible car même longueur)
                    for(int i=0; i <= m.groupCount(); i++) {
                        // affichage de la sous-chaîne captu1rée
                        System.out.println("Votre saisie   : " + m.group(i));
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
