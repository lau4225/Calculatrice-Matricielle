package sample;

import AlerteErreur.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Controller {
    @FXML
    GridPane matA;
    @FXML
    GridPane matB;

    private TextField[] tableau1 = new TextField[1000];  //tableau correspondant à la matrice A
    private TextField[] tableau2 = new TextField[1000];  //tableau correspondant à la matrice B

    private String resultat1 = "";  //nb lignes mat A
    private String resultat2 = "";  //nb colonnes mat A
    private String resultat3 = "";  //nblignes mat B
    private String resultat4 = "";  //nb colonnes mat B

    private TextField[][] tab2DA = new TextField[1000][1000];  //tableau 2D correspondant à la matrice A
    private TextField[][] tab2DB = new TextField[1000][1000];  //tableau 2D correspondant à la matrice B


    public void dialog(){
        matA.getChildren().clear();
        matB.getChildren().clear();

        resultat1 = size("A", "lignes");
        resultat2 = size("A", "colonnes");
        resultat3 = size("B", "lignes");
        resultat4 = size("B", "colonnes");

        int nbTextA = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
        int nbTextB = Integer.parseInt(resultat3)*Integer.parseInt(resultat4);

        try {
            creation(nbTextA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tableau1, matA, tab2DA);
            creation(nbTextB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tableau2, matB, tab2DB);
        }catch (Exception e){ }
    }

    public String size (String lettre, String choix){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Matrice " + lettre);
        dialog.setHeaderText("Création");
        dialog.setContentText("Entrez le nombre de " + choix);
        String resultat = dialog.showAndWait().get();
        if (resultat.equals("")){
            dialog.setHeaderText("Vous devez entrer un nombre!");
            resultat = dialog.showAndWait().get();
        }
        return resultat;
    }

    public void creation(int nbTextField, int nbLignes, int nbColonnes, TextField[] tableau, GridPane matrice, TextField[][] tableau2D){

        for (int i = 0; i< nbTextField; i++){

                tableau[i] = new TextField();
                int col = i%(nbColonnes);
                int row = (i/nbColonnes);
                matrice.add(tableau[i], col, row);
        }
        //Creation des tableaux 2D
        int k = 0;
        for (int i = 0; i < nbLignes; i++){

            for (int j = 0; j < nbColonnes; j++){

                tableau2D[i][j] = tableau[k];
                k++;
            }
        }
    }

    @FXML
    Button somme;
    public void addition(){

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

        if (entier == false && entier2 == false){
            if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {

                // on a juste 2 matrices alors pas besoin de passer en parametres
                if (casesVides()){ emptyDouble(); }
                else {
                    int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);
                    int[] resultat = new int[nombre];

                    for (int i = 0; i < nombre; i++) {
                        try {
                            resultat[i] = Integer.parseInt(tableau1[i].getText()) + Integer.parseInt(tableau2[i].getText());
                        }catch (Exception e){ emptyDouble(); } }
                    reponse(resultat, "ADDITION");
                }
            }
            else {
                Same alert = new Same();
                alert.creationAlerte();
            }
        }

    }

    @FXML
    Button difference;
    public void soustraction(){

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

        if (entier == false && entier2 == false){
            if (resultat1.equals(resultat3) && resultat2.equals(resultat4)){

                if (casesVides()){ emptyDouble(); }
                else {
                    int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);
                    int[] resultat = new int[nombre];
                    for (int i = 0; i < nombre; i++) {
                        try {
                            resultat[i] = Integer.parseInt(tableau1[i].getText()) - Integer.parseInt(tableau2[i].getText());
                        }catch (Exception e){ emptyDouble(); } }
                    reponse(resultat, "SOUSTRACTION);
                }
            }
            else {
                Same alert = new Same();
                alert.creationAlerte();

            }
        }
    }

    @FXML
    Button hadam;
    public void hadamard(){

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

        if (entier == false && entier2 == false){
            if (resultat1.equals(resultat3) && resultat2.equals(resultat4)){

                if (casesVides()){ emptyDouble(); }
                else {
                    int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);
                    int[] resultat = new int[nombre];
                    for (int i = 0; i < nombre; i++) {
                        try {
                            resultat[i] = Integer.parseInt(tableau1[i].getText()) * Integer.parseInt(tableau2[i].getText());
                        }catch (Exception e){ emptyDouble(); } }
                    reponse(resultat, "PRODUIT HADAMARD);
                }
            }
            else{
                Same alert = new Same();
                alert.creationAlerte();
            }
        }
    }

    @FXML
    Button scalaireA;
    @FXML
    TextField produitNbA;
    @FXML
    Button scalaireB;
    @FXML
    TextField produitNbB;
    public void multiScalaire(TextField[] tableau, int lignes, int colonnes, TextField facteur, String lettre) {

        boolean entier = caractere(tableau, lignes, colonnes, lettre);

        int[] corolaire = new int[lignes * colonnes];
        boolean vide = false;

        if (entier == false){
            if (!facteur.getText().isEmpty()) { //Si la case n'est pas vide pour 1 seule matrice
                for (int i = 0; i < corolaire.length; i++) { //regarder s'il y a des cases vides
                    if(tableau[i].getText().isEmpty()){ //Si oui, vide est vrai
                        vide = true;
                    }}

                if (vide){ emptySolo(); }
                else {
                    for (int i = 0; i < corolaire.length; i++) {
                        try {
                            corolaire[i] = Integer.parseInt(tableau[i].getText()) * Integer.parseInt(facteur.getText());
                        } catch (Exception e) {
                            emptySolo();
                        }
                    }
                    solo(corolaire, colonnes, "MULTIPLICATION PAR UN SCALAIRE");
                    facteur.setText("");
                }

            }
        }
    }

    public void multiScalaireA() { multiScalaire(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), produitNbA, "A"); }
    public void multiScalaireB() { multiScalaire(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), produitNbB, "B"); }

    @FXML
    Button transpoA;
    @FXML
    Button transpoB;
    public void transposer(TextField[][] tableau, int lignes, int colonnes, TextField[] tab, String lettre){

        boolean entier = caractere(tab, lignes, colonnes, lettre);

        if (entier == false){
            int[][] resultat = new int[colonnes][lignes];

            for (int i = 0; i < colonnes; i++) {

                for (int j = 0; j < lignes; j++) {
                    resultat[i][j] = Integer.parseInt(tableau[j][i].getText());
                }
            }
            repTranspo(resultat, colonnes, lignes);
        }
    }

    public void transposerA(){ transposer(tab2DA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tableau1, "A");}
    public void transposerB(){ transposer(tab2DB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tableau2, "B");}

    @FXML
    Button detA;
    @FXML
    Button detB;

    public void determinant(TextField[] tableau, int lignes, int colonnes, String lettre, TextField[][] tab){//Laurie rendu ici

        boolean entier = caractere(tableau, lignes, colonnes, lettre);

        if (entier == false){
            //regarder si la matrice est carré
            if (lignes == colonnes){
                //transformer toutes les textfield
                int[] tempo = new int[lignes*colonnes];
                int det = 0;

                for (int i=0; i< tempo.length; i++){
                    tempo[i] = Integer.parseInt(tableau[i].getText());
                }
                switch (lignes){
                    case 1 : repDeterminant(tempo[0], lettre); break;
                    case 2 :
                        det = (tempo[0]*tempo[3]) - (tempo[1]*tempo[2]);
                        repDeterminant(det, lettre);
                        break;

                    case 3 :
                        det = ((tempo[0]*tempo[4]*tempo[8]) + (tempo[1]*tempo[5]*tempo[6]) + (tempo[2]*tempo[3]*tempo[7])) -
                                ((tempo[6]*tempo[4]*tempo[2]) + (tempo[7]*tempo[5]*tempo[0]) + (tempo[8]*tempo[3]*tempo[1]));
                        repDeterminant(det, lettre);
                        break;

                    default :                 //À FAIRE POUR DES MATRICES D'ORDRE SUPÉRIEUR A 3

                        int line = lignes;
                        int column  = colonnes;
                        int[][] calculDet = new int[line][column];

                        if (line == 2 && column == 2){  /// pas ça

                            det = (tempo[0]*tempo[3]) - (tempo[1]*tempo[2]);
                            repDeterminant(det, lettre);
                        }
                        while (line !=2 && column !=2){

                            for (int i = 0; i < lignes; i++){
                                calculDet[i] = sousMatrice(tempo, line, column);
                                line--;
                                column--;
                            }


                        }
                }
            }else{
                Carrée alert = new Carrée();
                alert.creationAlerte();
            }
        }
    }

    public int[] sousMatrice(int[] mat, int lignes, int colonnes){

        int dimension = (lignes -1)*(colonnes-1);

        int[] sousMat = new int[dimension];
        int[] ligneDet = new int[colonnes];

        //ligne déteminant
        for (int i = 0; i < colonnes; i++){
            ligneDet[i] = mat[i];
        }
        //sous matrice
        for (int i = 0; i < lignes - 1; i++){
            for (int j = 0; j < colonnes -1 ; j++);{

                sousMat[i] = mat[i + colonnes];
            }
        }
        //fusion élément de la ligne et sous matrice
        for (int i = 0; i < dimension; i++){

            sousMat[i] = ligneDet[i]*sousMat[i];

        }

        return sousMat;
    }

    public void determinantA(){determinant(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A", tab2DA);}
    public void determinantB(){determinant(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B", tab2DB);}

    public void repDeterminant(int determinant, String lettre){

        Label reponse = new Label(Integer.toString(determinant));

        reponse.setScaleX(1.4);reponse.setScaleY(1.4);
        reponse.setAlignment(Pos.CENTER);

        Dialog dialog = new Dialog();
        dialog.setHeaderText("Le déterminat de la matrice " + lettre + " est : ");
        dialog.getDialogPane().setContent(reponse);
        dialog.setTitle("DÉTERMINANT");
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );

        dialog.getDialogPane().setMinSize(200, 200);
        dialog.showAndWait();
    }

        public void multiplier() {//Condition pour l'addition : si A (m x n) et B (n x p)

            boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
            boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

            if (entier == false && entier2 == false){
                if (resultat2.equals(resultat3)){
                    int[][] resultat = new int[Integer.parseInt(resultat1)][Integer.parseInt(resultat4)];

                    int repC = 0;
                    int produit = 0;

                    for (int i = 0; i < Integer.parseInt(resultat1); i++) { //nb de lignes

                        for (int j = 0; j < Integer.parseInt(resultat4); j++) {  // nb colonnes

                            for (int k = 0; k < Integer.parseInt(resultat2); k++) {  //represente k dans la definition
                                try {

                                    produit = Integer.parseInt(tab2DA[i][k].getText()) * Integer.parseInt(tab2DB[k][j].getText());
                                    repC = repC + produit;

                                } catch (Exception e) {
                                    System.out.println("yolo");
                                }
                            }
                            resultat[i][j] = repC;
                            repC = 0;
                        }
                    }
                    repMulti(resultat, "PRODUIT MATRICIEL");

                } else {
                    Dimension alert1 = new Dimension();
                    alert1.creationAlerte();
                }
            }
        }


    @FXML
    TextField expoB;
    @FXML
    TextField expoA;

    public void puissance(TextField power, int lignes, int colonnes, TextField[][] tableau, TextField[] tab, String lettre){ //doit être carrée

       boolean entier =  caractere(tab, lignes, colonnes, lettre);

        int produit = 0;
        int repC = 0;
        int[][] resultat = new int[lignes][colonnes];
        int exposant  = Integer.parseInt(power.getText());

        if (entier == false){
            if (lignes == colonnes){

                if (exposant == 0){
                    for (int i = 0; i < lignes; i++){
                        for (int j = 0; j < colonnes; j++){
                            resultat[i][j] = 1;
                        }
                    }
                }

                if (exposant == 1){
                    for (int i = 0; i < lignes; i++){
                        for (int j = 0; j < colonnes; j++){
                            resultat[i][j] = Integer.parseInt(tableau[i][j].getText());
                        }
                    }
                }

                if (exposant > 1){

                    int nbrMulti = 1;

                    while (nbrMulti < exposant){

                        for (int i = 0; i < lignes; i++) { //nb de lignes

                            for (int j = 0; j < colonnes; j++) {  // nb colonnes

                                for (int k = 0; k < lignes; k++){

                                    try {
                                        produit = Integer.parseInt(tableau[i][k].getText())*Integer.parseInt(tableau[k][j].getText());
                                        repC = repC + produit;

                                    } catch (Exception e) {
                                        System.out.println("yolo");
                                    }
                                }
                                resultat[i][j] = resultat[i][j] + repC;
                                repC =0;
                            }
                        }
                        nbrMulti++;
                    }
                }
                repMulti(resultat, "PUISSANCE");
                power.setText("");
            }
            else{
                Carrée alert = new Carrée();
                alert.creationAlerte();
            }
        }
    }

    public void puissanceA(){puissance(expoA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tab2DA, tableau1, "A");}
    public void puissanceB(){puissance(expoB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tab2DB, tableau2, "B");}

    public void produitVectoriel(){

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

        int[] resultat = new int[3];


        if (entier == false && entier2 == false){
            if (Integer.parseInt(resultat2) ==  3 && Integer.parseInt(resultat4) == 3 && Integer.parseInt(resultat1) == 1
                    && Integer.parseInt(resultat3) == 1){

                for(int i = 0; i < resultat.length; i++){

                    resultat[0] = (Integer.parseInt(tableau1[1].getText())*Integer.parseInt(tableau2[2].getText())) -
                            (Integer.parseInt(tableau1[2].getText())*Integer.parseInt(tableau2[1].getText()));

                    resultat[1] = (Integer.parseInt(tableau1[2].getText())*Integer.parseInt(tableau2[0].getText())) -
                            (Integer.parseInt(tableau1[0].getText())*Integer.parseInt(tableau2[2].getText()));

                    resultat[2] = (Integer.parseInt(tableau1[0].getText())*Integer.parseInt(tableau2[1].getText())) -
                            (Integer.parseInt(tableau1[1].getText())*Integer.parseInt(tableau2[0].getText()));
                }
                reponse(resultat, "PRODUIT VECTORIEL");
            }
            else {
                Vecto alert = new Vecto();
                alert.creationAlerte();
            }
        }
    }

    @FXML
    Button tensoriel;
    public void tensoriel() {  // matA mxn  & matB  pxq

        // resultat dimensions mp x nq
        int m = Integer.parseInt(resultat1);
        int n = Integer.parseInt(resultat2);
        int p = Integer.parseInt(resultat3);
        int q = Integer.parseInt(resultat4);

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

        if (entier == false && entier2 == false){


            // resultat dimensions mp x nq
            int m = Integer.parseInt(resultat1);                        // presque parfait, marche pas si 2X3 et 3X2
            int n = Integer.parseInt(resultat2);
            int p = Integer.parseInt(resultat3);
            int q = Integer.parseInt(resultat4);

            int[] etape1 = new int[p*q];

            int[][][] etape3 = new int[m*n][p][q];
            int[][][][] resultat = new int[m][n][p][q];

            for (int j = 0; j < m*n ; j++) { //nb de valeur tabA
                int[][] etape2 = new int[p][q];

                //multiplication par un scalaire dans un tableau 1D
                for (int i = 0; i < etape1.length; i++) {
                    try {
                        etape1[i] = Integer.parseInt(tableau1[j].getText()) * Integer.parseInt(tableau2[i].getText()); //premiere valeur tabA avec toutes les valeurs tabB
                    } catch (Exception e) {
                        emptySolo();
                    }
                }

                //on prend ce tableau et on le transforme en 2D
                for (int k = 0; k < etape1.length; k++){
                    etape2[k/q][k%q] = etape1[k];
                }

            //On prend ce tableau 2D et on le met dans un tableau de tableau 2D
            etape3[j] = etape2;
        }


        // on retransforme ce tableau 1D en tableau 2D
        for (int r = 0; r < etape3.length; r++){
            //ajouter les valeurs au gridpane
            resultat [r/n][r%n] = etape3[r];
        }

        GridPane reponse = new GridPane();


        for (int i = 0; i< m ; i++){ //lignes
            for (int j =0; j < n ; j++){ //colonnes
                for (int k=0; k < p ; k++){ //colonnes total
                    for (int c = 0; c < q ; c++){ //lignes total
                        //position  matA  lignes, colonnes   mat B lignes colonnes    coordonnées colonnes, lignes
                        reponse.add(new Label(String.valueOf(resultat[i][j][k][c])), c + (j*q), k + (i*p));
        }
            affichage(reponse, "PRODUIT TENSORIEL");

    }


    public void repMulti(int[][] corolaire, String operation){            


        GridPane reponse = new GridPane();

        for (int i = 0; i< Integer.parseInt(resultat4); i++){
            for (int j =0; j < Integer.parseInt(resultat1); j++){
                reponse.add(new Label(String.valueOf(corolaire[j][i])), i, j);

            }
        }

        affichage(reponse, operation);

    }



        //réponse d'une opération sur deux matrices
    public void reponse(int[] corolaire,String operation){

        GridPane reponse = new GridPane();

        for (int i = 0; i< corolaire.length; i++){

            reponse.add(new Label(String.valueOf(corolaire[i])), i%Integer.parseInt(resultat2), (int) (i/ Integer.parseInt(resultat2)));
        }

        affichage(reponse, operation);

    }

        //reponse d'une opération sur une seule matrice
    public void solo(int[] corolaire, int colonnes, String operation){

        GridPane reponse = new GridPane();

        for (int i = 0; i< corolaire.length; i++){
            //ajouter les valeurs au gridpane
            reponse.add(new Label(String.valueOf(corolaire[i])), i%colonnes, (int) (i/colonnes));
        }

        affichage(reponse, operation);

    }

    public void repTranspo(int[][] corolaire, int lignes, int colonnes){

        GridPane reponse = new GridPane();

        for (int i = 0; i< lignes; i++){
            for (int j =0; j < colonnes; j++){
                reponse.add(new Label(String.valueOf(corolaire[i][j])), j, i);

            }
        }

        affichage(reponse, "TRANSPOSITION");

    }

    public void emptyDouble(){ //message d'erreur qui va afficher si toutes les cases ne sont pas remplies

        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Information importante");
        alerte.setHeaderText("Vous devez remplir toutes les cases avant de pouvoir effectuer une opération!");
        alerte.setContentText(
                "Remplissez les matrices A et B avec des nombres entiers seulement! ");
        alerte.showAndWait();
    }

    public void emptySolo(){ //message d'erreur qui va afficher si toutes les cases ne sont pas remplies

        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Information importante");
        alerte.setHeaderText("Vous devez remplir toutes les cases avant de pouvoir effectuer une opération!");
        alerte.setContentText(
                "Remplissez la matrice avec des nombres entiers seulement! ");
        alerte.showAndWait();
    }

    public boolean casesVides() {

        boolean vide = false;

        int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);   //position
        for (int i = 0; i < nombre; i++) { //regarder s'il y a des cases vides
            if (tableau1[i].getText().isEmpty() || tableau2[i].getText().isEmpty()) { //Si oui, vide est vrai
                vide = true;
            }

        }
        return vide;
    }


    public void affichage(GridPane reponse, String operation){

        reponse.setAlignment(Pos.CENTER);
        reponse.setVgap(10); reponse.setHgap(13);

        for (Node r: reponse.getChildren()) {
            r.setScaleX(1.4); r.setScaleY(1.4);

        }

        Dialog dialog = new Dialog();

        dialog.setResizable(true);
        dialog.setTitle(operation);
        dialog.setHeaderText("Matrice Résultante : ");
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("OK DONE", ButtonBar.ButtonData.OK_DONE)
        );
        dialog.getDialogPane().setMinSize(250, 200);

        dialog.showAndWait();
    }
    public boolean caractere(TextField[] tableau, int ligne, int colonne, String lettre){

        boolean mauvais = false;

        int nombre = ligne*colonne;

        for (int i = 0; i < nombre; i++) {

            char[] chars = tableau[i].getText().toCharArray();

            for (int j = 0; j < chars.length; j++){

                if (chars[j] < 48 || chars[j] > 57 ){

                    mauvais = true;
                }
            }

        }

        if (mauvais == true){
            Caractere alert = new Caractere();
            alert.setLettre(lettre);
            alert.creationAlerte();
        }
        return mauvais;

    }

//si reste temps
    //dialogue héritage
    //case vide héritage
}
