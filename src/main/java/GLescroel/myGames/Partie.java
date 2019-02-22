package GLescroel.myGames;

import java.util.List;

public class Partie {

    private String nomJeu;
    private String mode;
    private int nbEssai;
    private int nbDigit;
    private Parametres parametres;

    JoueurHumain joueur;
    JoueurOrdi ordi;

    public Partie() {
        parametres = new Parametres();
        parametres.runInit();
    }


    public void startPartie(){

            initPartie();
            initJoueurs();
            runJeu();

    }

    public void initJoueurs() {
        joueur = new JoueurHumain(Interaction.askText("prénom"));
        ordi = new JoueurOrdi("Ordi");

    }

    public void initPartie(){

        choisirJeu(parametres.getJeuxPossibles());
        choisirMode(parametres.getModesPossibles());
        choisirNbDigit(parametres.getNbDigitMinPossible(), parametres.getNbDigitMaxPossible());
        choisirNbEssai(parametres.getNbEssaisMinPossible(), parametres.getNbEssaisMaxPossible());
    }

    public void choisirJeu(List<String> listeJjeuxPossibles)
    {
        String[] jeuxPossibles = new String[listeJjeuxPossibles.size()];
        for(int j = 0 ; j < listeJjeuxPossibles.size() ; j++)
            jeuxPossibles[j] = listeJjeuxPossibles.get(j);

        int jeuChoisi = Interaction.askSomething("Partie", jeuxPossibles)-1;
        nomJeu = listeJjeuxPossibles.get(jeuChoisi);

        //System.out.println("jeux choisi : " + nomJeu);
    }

    public void choisirMode(List<String> listeModesPossibles)
    {
        String[] modesPossibles = new String[listeModesPossibles.size()];
        for(int j = 0 ; j < listeModesPossibles.size() ; j++)
            modesPossibles[j] = listeModesPossibles.get(j);

        int modeChoisi = Interaction.askSomething("Mode", modesPossibles)-1;
        mode = listeModesPossibles.get(modeChoisi);

        //System.out.println("Mode choisi : " + mode);
    }

    public void choisirNbDigit(int nbDigitMin, int nbDigitMax)
    {
        nbDigit = Interaction.askQuantity("de digit", nbDigitMin, nbDigitMax);
        //System.out.println("Nb digit choisi : " + nbDigit);
    }

    public void choisirNbEssai(int nbEssaisMin, int nbEssaisMax)
    {
        nbEssai = Interaction.askQuantity("d'essais", nbEssaisMin, nbEssaisMax);
        //System.out.println("Nb essais choisi : " + nbEssai);
    }


    public void runJeu() {
        System.out.println("IN PROGRESS");

        if(nomJeu.equals("MASTERMIND"))
        {
            Mastermind myMastermind = new Mastermind(joueur, ordi, mode, nbDigit, nbEssai);
            myMastermind.runMastermind();
        }
        else if(nomJeu.equals("PLUSMOINS"))
        {
            PlusMoins myPlusMoins = new PlusMoins(joueur, ordi, mode, nbDigit, nbEssai);
            myPlusMoins.runPlusMoins();
        }

    }


    /////////////// GETTER & SETTER //////////////////////////////
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNomJeu() {
        return nomJeu;
    }

    public void setNomJeu(String nomJeu) {
        this.nomJeu = nomJeu;
    }

    public int getNbEssai() {
        return nbEssai;
    }

    public void setNbEssai(int nbEssai) {
        this.nbEssai = nbEssai;
    }

    public int getNbDigit() {
        return nbDigit;
    }

    public void setNbDigit(int nbDigit) {
        this.nbDigit = nbDigit;
    }
    /////////////// fin GETTER & SETTER //////////////////////////////

}
