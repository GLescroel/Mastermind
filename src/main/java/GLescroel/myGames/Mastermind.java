package GLescroel.myGames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Mastermind extends Jeu {

    String mode;
    int nbDigit;
    int nbEssai;
    JoueurOrdi ordi;
    JoueurHumain joueur;

    private List<String[]> listeDesPossibles = new ArrayList<>();

    public Mastermind(JoueurHumain joueur, JoueurOrdi ordi, String mode, int nbDigit, int nbEssai) {
        this.mode = mode;
        this.nbDigit = nbDigit;
        this.nbEssai = nbEssai;
        this.joueur = joueur;
        this.ordi = ordi;
    }

    public void runMastermind(){

        if(mode.equals("Defenseur"))
            runMasterMindDefenseur();
        else if(mode.equals("Challenger"))
            runMasterMindChallenger();
        else if(mode.equals("Duel")) {
            runMasterMindDuel();
        }

        affichageResultat(joueur, ordi);

    }

    public void runMasterMindDefenseur(){

        initListePossibles();

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit));

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


    public void runMasterMindChallenger() {

        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit));

        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit), null));
            essai++;
        } while (essai < nbEssai && !joueur.isCombinaisonTrouvee());

        System.out.println("Nombre de tentatives : " + essai);
    }

    public void runMasterMindDuel() {

        initListePossibles();

        joueur.setCombinaisonSecrete(joueur.joueurChoisitCombiSecrete(nbDigit));
        ordi.setCombinaisonSecrete(ordi.joueurChoisitCombiSecrete(nbDigit));

        String[] previousResult = new String[nbDigit];
        int essai = 0;
        do {
            joueur.setCombinaisonTrouvee(evaluerMasterMindProposition(ordi.getCombinaisonSecrete(), joueur.joueurProposeCombi(nbDigit), null));

            String[] ordiProposition = ordi.ordiProposeCombiMasterMind(listeDesPossibles);
            ordi.setCombinaisonTrouvee(evaluerMasterMindProposition(joueur.getCombinaisonSecrete(), ordiProposition, previousResult));
            updateListeDesPossibles(ordiProposition, previousResult);

            essai++;
        } while (essai < nbEssai && !ordi.isCombinaisonTrouvee() && !joueur.isCombinaisonTrouvee());

        System.out.println("Nombre de tentatives : " + (essai));
    }

    public void initListePossibles(){
        String valueMaxString = "";
        for(int v = 0 ; v < nbDigit ; v++)
            valueMaxString += "9";

        long possibleMax = Long.valueOf(valueMaxString);

        for(long choice = 0 ; choice <= possibleMax ; choice++)
        {
            String value = String.valueOf(choice);
            //System.out.println("value = " + value);
            String[] possibility = new String[nbDigit];
            for(int c = 1 ; c <= nbDigit ; c++)
            {
                if(c <= value.length())
                    possibility[nbDigit - c] = String.valueOf(value.charAt(value.length() - c));
                else
                    possibility[nbDigit - c] = "0";

                //System.out.println("possibility = " + possibility[nbCar-c]);
            }

            listeDesPossibles.add(possibility);

            /*String aff = "";
            for(int i = 0 ; i < nbCar ; i++)
                aff += possibility[i];
            System.out.println("résultat à ajouter : " + aff);*/

        }

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

    private boolean evaluerMasterMindProposition(String[] secretValue, String[] tryValue, String[] result) {

        boolean allGood = true;
        int nbGood = 0;
        int nbPresent = 0;

        String secretValueString = "";
        for(int c = 0 ; c < secretValue.length ; c++)
            secretValueString += secretValue[c];

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
                            String thisProposalString = "";
                            for (int c = 0; c < thisProposal.length; c++)
                                thisProposalString += thisProposal[c];

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
                    String thisProposalString = "";
                    boolean proposalToDelete = false;

                    for (int c = 0; c < thisProposal.length; c++)
                        thisProposalString += thisProposal[c];

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

        System.out.println("nb deletions : " + indexToDelete.size());
        System.out.println("nb possibles restants : " + listeDesPossibles.size());
    }



}
