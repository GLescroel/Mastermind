package GLescroel.myGames;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static GLescroel.myGames.Log.DEBUG_DEV;
import static GLescroel.myGames.Log.TRACE;
import static GLescroel.myGames.Tools.*;

/**
 * Classe Mastermind étend la Classe abstraite Jeu
 * contient le déroulé et les méthodes spécifiques du jeu MasterMind
 */
public class Mastermind extends Jeu {

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
        TRACE("MasterMind() (constructor)");
        this.nbValeur = nbValeur;
    }

    /**
     * runMasterMind() exécute le jeu MasterMind
      */
    public void runMastermind(){
        TRACE("MasterMind.runMasterMind()");

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
        TRACE("MasterMind.runMasterMindDefenseur()");

        initListePossibles();

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit, nbValeur));

        ordi.setPreviousResult(new String[nbDigit]);
        int essai = 0;
        do {
            String[] ordiProposition = ordi.joueurProposeCombi(nomJeu, nbDigit, nbValeur);
            ordi.setCombinaisonTrouvee(evaluerMasterMindProposition(joueur.getCombinaisonSecrete(), ordiProposition, ordi.getPreviousResult()));
            updateListeDesPossibles(ordiProposition, ordi.getPreviousResult());
            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee());
        System.out.println("Nombre de tentatives : " + essai);
    }


    /**
     * runMasterMindChallenger() exécute le jeu MasterMind en mode challenger
     * Le joueur doit deviner la combinaison secrète de l'ordinateur
     */
    public void runMasterMindChallenger() {
        TRACE("MasterMind.runMasterMindChallenger()");

        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit, nbValeur));

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nomJeu, nbDigit, nbValeur), null));
            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee());

        System.out.println("Nombre de tentatives : " + essai);
    }

    /**
     * runMasterMindDuel() exécute le jeu MasterMind en mode duel
     * Les joueurs ordinateur et humain doivent deviner la combinaison secrète de l'autre joueur en premier
     */
    public void runMasterMindDuel() {
        TRACE("MasterMind.runMasterMindDuel()");

        initListePossibles();

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit, nbValeur));
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit, nbValeur));

        ordi.setPreviousResult(new String[nbDigit]);
        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nomJeu, nbDigit, nbValeur), null));

            String[] ordiProposition = ordi.joueurProposeCombi(nomJeu, nbDigit, nbValeur);
            ordi.setCombinaisonTrouvee(evaluerMasterMindProposition(joueur.getCombinaisonSecrete(), ordiProposition, ordi.getPreviousResult()));
            updateListeDesPossibles(ordiProposition, ordi.getPreviousResult());

            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee() && !joueur.isCombinaisonTrouvee());

        System.out.println("Nombre de tentatives : " + (essai));
    }

    /**
     * initListePossibles() crée la liste de toutes les combinaisons MAsterMind possibles
     * en base 10 et en fonction du nombre de caractères de la combinaison
     */
    public void initListePossibles(){
        TRACE("MasterMind.initListePossibles()");

        String valueMaxString = "";
        for(int v = 0 ; v < nbDigit ; v++)
            valueMaxString += String.valueOf(nbValeur-1);

        long possibleMax = Long.valueOf(valueMaxString);

        for(long choice = 0 ; choice <= possibleMax ; choice++)
        {
            boolean valueToAdd = true;
            String value = String.valueOf(choice);
            //DEBUG_DEV("valeur max : " + value);
            String[] possibility = new String[nbDigit];

            for(int c = 1 ; c <= nbDigit ; c++)
            {
                if(c <= value.length())
                    possibility[nbDigit - c] = String.valueOf(value.charAt(value.length() - c));
                else
                    possibility[nbDigit - c] = "0";

                if(Integer.valueOf(possibility[nbDigit - c]) >= nbValeur)
                    valueToAdd = false;

                //DEBUG_DEV("possibility = " + possibility[nbDigit-c]);
            }

            if(valueToAdd == true)
                ordi.addToTryList(possibility);

            //DEBUG_DEV("résultat à ajouter : " + convertArrayToString(possibility));
        }

        DEBUG_DEV("nb possibilités : " + ordi.getTryList().size());

        /*for(int l = 0 ; l < ordi.getTryList().size() ; l++)
            DDEBUG_DEV("ajouté : " + convertArrayToString(ordi.getTryList().get(l)));*/

    }

    /**
     * evaluerMasterMindProposition() évalue la proposition MasterMind en fonction de la combinaison secrète
     * @param secretValue combinaison secrète du défenseur
     * @param tryValue combinaison proposée par le challenger
     * @param result résultat de l'évaluation (nb bien placés, nb présents mal placés)
     * @return boolean allGood qui indique si tout est bien placé ou non
     */
    public static boolean evaluerMasterMindProposition(String[] secretValue, String[] tryValue, String[] result) {
        TRACE("MasterMind.evaluerMasterMindProposition()");

        boolean allGood = true;
        int nbGood = 0;
        int nbPresent = 0;

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
        TRACE("MasterMind.updateListeDesPossibles()");

        List<Integer> indexToDelete = new CopyOnWriteArrayList<Integer>();

        //DEBUG_DEV("liste des possible dans update = " + listeDesPossibles.size());
        for(int proposal = 0 ; proposal < ordi.getTryList().size() ; proposal++) {
            String[] thisProposal = ordi.getTryList().get(proposal);

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
                    //DEBUG_DEV("previousResult[0] : " + previousResult[0]);
                    int nbPlaced = 0;
                    for (int c = 0; c < ordiProposition.length; c++)
                        if (thisProposal[c].equals(ordiProposition[c]))
                            nbPlaced++;

                    if (nbPlaced < Integer.valueOf(previousResult[0]))
                        indexToDelete.add(proposal);
                    else {
                        if(!previousResult[1].equals("0"))
                        {
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
                        //DEBUG_DEV("à conserver : " + convertArrayToString(thisProposal));
                    }
                } else if (!previousResult[1].equals("0")) {

                    boolean proposalToDelete = false;

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
            //DEBUG_DEV("deletion : " +convertArrayToString(ordi.getTryList().get(deletion));
            ordi.removeFromTryList(indexToDelete.get(deletion).intValue());
        }

        if (Parametres.getRunMode().equals("DEV")){
            System.out.println("nb deletions : " + indexToDelete.size());
            System.out.println("nb possibles restants : " + ordi.getTryList().size());
        }
    }


}
