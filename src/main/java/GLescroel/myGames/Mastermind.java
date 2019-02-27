package GLescroel.myGames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static GLescroel.myGames.Tools.convertArrayToString;

/**
 * Classe Mastermind étend la Classe abstraite Jeu
 * contient le déroulé et les méthodes spécifiques du jeu MasterMind
 */
public class Mastermind extends Jeu {

    private List<String[]> listeDesPossibles = new ArrayList<>();

    /**
     * PlusMoins() constructor de la classe MasterMind
     * @param nomJeu nom du jeu
     * @param joueur joueur humain
     * @param ordi joueur ordinateur
     * @param mode mode de jeu
     * @param nbDigit nombre de caractères des combinaisons
     * @param nbValeur nombre de valeurs possibles pour chaque caractère
     * @param nbEssai nombre d'essais max pour trouver la solution
     */
    /////Constructor
    public Mastermind (String nomJeu, JoueurHumain joueur, JoueurOrdi ordi, String mode, int nbDigit, int nbValeur, int nbEssai) {
        super(nomJeu, joueur, ordi, mode, nbDigit, nbEssai);
        this.nbValeur = nbValeur;
    }

    /**
     * runMasterMind() exécute le jeu MasterMind
      */
    public void runMastermind(){

        System.out.println(nbValeur);

        if(mode.equals(modeDefenseur))
            runMasterMindDefenseur();
        else if(mode.equals(modeChallenger))
            runMasterMindChallenger();
        else if(mode.equals(modeDuel)) {
            runMasterMindDuel();
        }

        affichageResultat(joueur, ordi, mode);
    }

    /**
     * runMasterMindDefenseur() exécute le jeu MasterMind en mode défenseur
     * L'ordinateur doit deviner la combinaison secrète du joueur
     */
    public void runMasterMindDefenseur(){

        initListePossibles();

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit, nbValeur));

        String[] previousResult = new String[nbDigit];
        int essai = 0;
        do {
            String[] ordiProposition = ordi.ordiProposeCombiMasterMind(listeDesPossibles);
            ordi.setCombinaisonTrouvee(evaluerMasterMindProposition(joueur.getCombinaisonSecrete(), ordiProposition, previousResult));
            updateListeDesPossibles(ordiProposition, previousResult);
            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);
    }


    /**
     * runMasterMindChallenger() exécute le jeu MasterMind en mode challenger
     * Le joueur doit deviner la combinaison secrète de l'ordinateur
     */
    public void runMasterMindChallenger() {

        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit, nbValeur));

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit, nbValeur), null));
            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee());

        System.out.println("Nombre de tentatives : " + essai);
    }

    /**
     * runMasterMindDuel() exécute le jeu MasterMind en mode duel
     * Les joueurs ordinateur et humain doivent deviner la combinaison secrète de l'autre joueur en premier
     */
    public void runMasterMindDuel() {

        initListePossibles();

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit, nbValeur));
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit, nbValeur));

        String[] previousResult = new String[nbDigit];
        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit, nbValeur), null));

            String[] ordiProposition = ordi.ordiProposeCombiMasterMind(listeDesPossibles);
            ordi.setCombinaisonTrouvee(evaluerMasterMindProposition(joueur.getCombinaisonSecrete(), ordiProposition, previousResult));
            updateListeDesPossibles(ordiProposition, previousResult);

            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee() && !joueur.isCombinaisonTrouvee());

        System.out.println("Nombre de tentatives : " + (essai));
    }

    /**
     * initListePossibles() crée la liste de toutes les combinaisons MAsterMind possibles
     * en base 10 et en fonction du nombre de caractères de la combinaison
     */
    public void initListePossibles(){
        String valueMaxString = "";
        for(int v = 0 ; v < nbDigit ; v++)
            valueMaxString += String.valueOf(nbValeur-1);

        long possibleMax = Long.valueOf(valueMaxString);

        for(long choice = 0 ; choice <= possibleMax ; choice++)
        {
            boolean valueToAdd = true;
            String value = String.valueOf(choice);
            //System.out.println("value = " + value);
            String[] possibility = new String[nbDigit];
            for(int c = 1 ; c <= nbDigit ; c++)
            {
                if(c <= value.length())
                    possibility[nbDigit - c] = String.valueOf(value.charAt(value.length() - c));
                else
                    possibility[nbDigit - c] = "0";

                if(Integer.valueOf(possibility[nbDigit - c]) >= nbValeur)
                    valueToAdd = false;

                //System.out.println("possibility = " + possibility[nbCar-c]);
            }

            if(valueToAdd == true)
                listeDesPossibles.add(possibility);

            /*String aff = "";
            for(int i = 0 ; i < nbCar ; i++)
                aff += possibility[i];
            System.out.println("résultat à ajouter : " + aff);*/
        }

        if (Parametres.getRunMode().equals("DEV"))
            System.out.println("nb possibilités : " + listeDesPossibles.size());
        /*String affichage = "";
        for(int l = 0 ; l < tryList.size() ; l++)
        {
            affichage = "ajouté : ";
            for(int c = 0 ; c < nbCar ; c++)
                affichage += tryList.get(l)[c];
            System.out.println(affichage);
        }*/

    }

    /**
     * evaluerMasterMindProposition() évalue la proposition MasterMind en fonction de la combinaison secrète
     * @param secretValue combinaison secrète du défenseur
     * @param tryValue combinaison proposée par le challenger
     * @param result résultat de l'évaluation (nb bien placés, nb présents mal placés)
     * @return boolean allGood qui indique si tout est bien placé ou non
     */
    private boolean evaluerMasterMindProposition(String[] secretValue, String[] tryValue, String[] result) {

        boolean allGood = true;
        int nbGood = 0;
        int nbPresent = 0;

        /*String secretValueString = "";
        for(int c = 0 ; c < secretValue.length ; c++)
            secretValueString += secretValue[c];*/
        String secretValueString = convertArrayToString(secretValue);

        for(int i = 0 ; i < tryValue.length; i++)
        {
            if(tryValue[i].equals(secretValue[i]))
                nbGood++;
            else if(secretValueString.contains(tryValue[i]))
            {
                nbPresent++;
                allGood = false;
            }
            else
                allGood = false;
        }

        System.out.println("Nb ok : " + nbGood + " \\ nb presents : " + nbPresent);

        if(result != null) {
            result[0] = String.valueOf(nbGood);
            result[1] = String.valueOf(nbPresent);
        }

        return allGood;

    }

    /**
     * updateListeDesPossibles() met à jour la liste des combinaisons possibles
     * en fonction de l'évaluation de la dernière proposition de l'ordinateur,
     * par supression des combinaisons qui ne sont plus possibles
     * @param ordiProposition la proposition de l'ordinateur
     * @param previousResult le résultat de l'évaluation de cette proposition
     */
    public void updateListeDesPossibles(String[] ordiProposition, String[] previousResult) {

        List<Integer> indexToDelete = new CopyOnWriteArrayList<Integer>();

        //System.out.println("liste des possible dans update = " + listeDesPossibles.size());
        for(int proposal = 0 ; proposal < listeDesPossibles.size() ; proposal++) {
            String[] thisProposal = listeDesPossibles.get(proposal);

            if (thisProposal == ordiProposition)
                indexToDelete.add(proposal);
            else {
                if ((previousResult[0].equals("0")) && (previousResult[1].equals("0"))) {
                    boolean onePresent = false;
                    String thisProposalString = "";
                    for (int c = 0; c < thisProposal.length; c++)
                        thisProposalString += thisProposal[c];

                    for (int c = 0; c < ordiProposition.length; c++) {
                        if (thisProposalString.contains(ordiProposition[c]))
                            onePresent = true;
                    }
                    if (onePresent == true)
                        indexToDelete.add(proposal);
                } else if (!previousResult[0].equals("0")) {
                    //System.out.println("previousResult[0] : " + previousResult[0]);
                    int nbPlaced = 0;
                    for (int c = 0; c < ordiProposition.length; c++)
                        if (thisProposal[c].equals(ordiProposition[c]))
                            nbPlaced++;

                    if (nbPlaced < Integer.valueOf(previousResult[0]))
                        indexToDelete.add(proposal);
                    else {
                        if(!previousResult[1].equals("0"))
                        {
                            /*String thisProposalString = "";
                            for (int c = 0; c < thisProposal.length; c++)
                                thisProposalString += thisProposal[c];*/
                            String thisProposalString = convertArrayToString(thisProposal);

                            int nbPresent = 0;
                            for (int c = 0; c < ordiProposition.length; c++) {
                                if (!thisProposal[c].equals(ordiProposition[c]))
                                {
                                    if(thisProposalString.contains(ordiProposition[c]))
                                        nbPresent++;
                                }
                            }
                            if(nbPresent == 0)
                                indexToDelete.add(proposal);
                        }
                        /*String affiche = "";
                        for (int c = 0; c < ordiProposition.length; c++)
                            affiche += thisProposal[c];
                        System.out.println("à conserver : " + affiche);*/
                    }
                } else if (!previousResult[1].equals("0")) {

                    boolean proposalToDelete = false;

                    /*String thisProposalString = "";
                    for (int c = 0; c < thisProposal.length; c++)
                        thisProposalString += thisProposal[c];*/
                    String thisProposalString = convertArrayToString(thisProposal);

                    for (int c = 0; c < ordiProposition.length; c++) {
                        if (thisProposal[c].equals(ordiProposition[c]))
                            proposalToDelete = true;
                    }

                    int nbFound = 0;
                    for (int c = 0; c < ordiProposition.length; c++) {
                        if (thisProposalString.contains(ordiProposition[c]) == true)
                            nbFound++;
                    }
                    if (nbFound == 0)
                        proposalToDelete = true;

                    if (proposalToDelete == true)
                        indexToDelete.add(proposal);
                }
            }
        }

        for(int deletion = (indexToDelete.size()-1) ; deletion >= 0 ; deletion--)
        {
            String local_aff = "";
            for(int a = 0 ; a < listeDesPossibles.get(deletion).length ; a++)
                local_aff += listeDesPossibles.get(deletion)[a];
            //System.out.println("deletion : " +local_aff);

            listeDesPossibles.remove(indexToDelete.get(deletion).intValue());
        }

        if (Parametres.getRunMode().equals("DEV")){
            System.out.println("nb deletions : " + indexToDelete.size());
            System.out.println("nb possibles restants : " + listeDesPossibles.size());
        }
    }


}
