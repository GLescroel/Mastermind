package GLescroel.myGames;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Jeu{

/*    protected String[] combinaisonSecreteJoueur;
    protected String[] combinaisonSecreteOrdi;
    private boolean combinaisonTrouveeParOrdi;
    private boolean combinaisonTrouveeParJoueur;
*/

//    private int nbDigit;

    //////////////////// GETTER & SETTER//////////////////////////////////////////////////////////

/*    public int getNbDigit() {
        return nbDigit;
    }

    public void setNbDigit(int nbDigit) {
        this.nbDigit = nbDigit;
    }
*/
    /*public String[] getCombinaisonSecreteJoueur() {
        return combinaisonSecreteJoueur;
    }

    public void setCombinaisonSecreteJoueur(String[] combinaisonSecreteJoueur) {
        this.combinaisonSecreteJoueur = combinaisonSecreteJoueur;
    }

    public String[] getCombinaisonSecreteOrdi() {
        return combinaisonSecreteOrdi;
    }

    public void setCombinaisonSecreteOrdi(String[] combinaisonSecreteOrdi) {
        this.combinaisonSecreteOrdi = combinaisonSecreteOrdi;
    }

    public boolean isCombinaisonTrouveeParOrdi() {
        return combinaisonTrouveeParOrdi;
    }

    public void setCombinaisonTrouveeParOrdi(boolean combinaisonTrouveeParOrdi) {
        this.combinaisonTrouveeParOrdi = combinaisonTrouveeParOrdi;
    }

    public boolean isCombinaisonTrouveeParJoueur() {
        return combinaisonTrouveeParJoueur;
    }

    public void setCombinaisonTrouveeParJoueur(boolean combinaisonTrouveeParJoueur) {
        this.combinaisonTrouveeParJoueur = combinaisonTrouveeParJoueur;
    }*/

    //////////////////// fin GETTER & SETTER//////////////////////////////////////////////////////////



/*
    protected String[] joueurChoisitCombiSecrete() {
        System.out.println("Choisissez votre combinaison secrète : ");
        return joueurProposeCombi();
    }

    protected String[] joueurProposeCombi() {
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

    protected String[] ordiDefinitCombiSecrete() {
        String[] selectedString = new String[nbDigit];

        for(int i = 0 ; i < nbDigit; i++) {
            Random rand = new Random();
            int nbAleatoire = rand.nextInt(9 - 0 + 1) + 0;
            selectedString[i] = String.valueOf(nbAleatoire);
        }

        String affichage = "";
        for(int i = 0; i < nbDigit; i++)
            affichage += selectedString[i];
        System.out.println("(ordiValue : " + affichage + ")");

        return selectedString;
    }
*/
    protected void affichageResultat(JoueurHumain joueur, JoueurOrdi ordi) {


        if (joueur.isCombinaisonTrouvee() && ordi.isCombinaisonTrouvee())
            System.out.println("Egalité !");
        if (!joueur.isCombinaisonTrouvee() && !ordi.isCombinaisonTrouvee())
            System.out.println("Aucun gagnant !");
        else if (joueur.isCombinaisonTrouvee())
            System.out.println("Vous avez gagné !");
        else if (ordi.isCombinaisonTrouvee())
            System.out.println("L'ordinateur a gagné !");
        else if (!ordi.isCombinaisonTrouvee())
            System.out.println("L'ordinateur a perdu !");
        else
            System.out.println("Vous avez perdu !");
    }


}
