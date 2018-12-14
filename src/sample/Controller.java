package sample;

import AlerteErreur.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
        }catch (Exception e){ System.out.print("Exception dialog");}
    }

    public String size (String lettre, String choix){
        String resultat = "";
        boolean trouve = false;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Matrice " + lettre);
        dialog.setHeaderText("Création");
        dialog.setContentText("Entrez le nombre de " + choix);
        try {
            resultat = dialog.showAndWait().get();
        }catch (Exception e){
            System.out.print("il n'y a rien d'entré");
            dialog.close();
            return null;
        }
        while (trouve == false){

            for (int i = 0; i<resultat.length(); i++){

                if (resultat.charAt(i) < 48 || resultat.charAt(i) > 57 ){
                    dialog.setHeaderText("Vous devez entrer un nombre!");
                    resultat = dialog.showAndWait().get();
                }
                else {
                    trouve = true;
                }
            }

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
                    reponse(resultat, "SOUSTRACTION");
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
                    reponse(resultat, "PRODUIT HADAMARD");
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

        int[] corolaire = new int[lignes * colonnes];
        boolean vide = false;

            if (!facteur.getText().isEmpty()) { //Si la case n'est pas vide pour 1 seule matrice
                for (int i = 0; i < corolaire.length; i++) { //regarder s'il y a des cases vides
                    if (tableau[i].getText().isEmpty()) { //Si oui, vide est vrai
                        vide = true;
                    }
                }

                if (vide) {
                    emptySolo();
                } else {
                    boolean entier = caractere(tableau, lignes, colonnes, lettre);
                    if (entier == false) {
                        if (validFactor(facteur)==false){ facteur.setText(""); }
                        else {

                            for (int i = 0; i < corolaire.length; i++) {
                                try {
                                    corolaire[i] = Integer.parseInt(tableau[i].getText()) * Integer.parseInt(facteur.getText());
                                } catch (Exception e) {
                                }
                            }
                            solo(corolaire, colonnes, "MULTIPLICATION PAR UN SCALAIRE");
                            facteur.setText("");
                        }
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
        if (casesVidesSolo(tab, lignes*colonnes)){emptySolo();}
        else {
            boolean entier = caractere(tab, lignes, colonnes, lettre);
            if (entier == false) {
                int[][] resultat = new int[colonnes][lignes];

                for (int i = 0; i < colonnes; i++) {

                    for (int j = 0; j < lignes; j++) {
                        resultat[i][j] = Integer.parseInt(tableau[j][i].getText());
                    }
                }
                repTranspo(resultat, colonnes, lignes);
            }
        }
    }

    public void transposerA(){ transposer(tab2DA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tableau1, "A");}
    public void transposerB(){ transposer(tab2DB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tableau2, "B");}

    @FXML
    Button detA;
    @FXML
    Button detB;

    public void determinant(TextField[] tableau, int lignes, int colonnes, String lettre, TextField[][] tab2D){
            //regarder si la matrice est carré
            if (lignes == colonnes){
                if (casesVidesSolo(tableau, lignes*colonnes)){emptySolo();}
                else {
                boolean entier = caractere(tableau, lignes, colonnes, lettre);
                if (entier == false){

                    int[][] matrix = new int[lignes][colonnes];
                    for (int i = 0; i < lignes; i++) {
                        for (int j = 0; j < colonnes; j++) {
                            matrix[i][j] = Integer.parseInt(tab2D[i][j].getText());
                        }
                    }
                            repDeterminant(calculDet(matrix), lettre);
                            //https://gist.github.com/Cellane/398372/23a3e321daa52d4c6b68795aae093bf773ce2940
                    }
                }
            }
            else{
                Carrée alert = new Carrée();
                alert.creationAlerte(); }
        }


    public int calculDet(int[][] matrix){
        int det = 0;

        if (matrix.length == 1) {
            det = matrix[0][0];
            return (det);
        }

        if (matrix.length == 2) {
            det = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
            return (det);
        }

        for (int i = 0; i < matrix[0].length; i++) {
            int[][] temporary = new int[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporary[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporary[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            det += matrix[0][i] * Math.pow (-1, i) * calculDet (temporary);
        }
        return (det);

    }

    public void determinantA(){determinant(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A", tab2DA);}
    public void determinantB(){determinant(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B", tab2DB);}

    public void repDeterminant(int determinant, String lettre){

        Label reponse = new Label(Integer.toString(determinant));

        reponse.setScaleX(1.4);reponse.setScaleY(1.4);
        reponse.setAlignment(Pos.CENTER);

        Button print = new Button("Imprimer");
        print.setAlignment(Pos.BOTTOM_CENTER);
        print.setMaxSize(100,18);
        print.setScaleX(1.2);print.setScaleY(1.2);

        VBox vBox = new VBox(reponse, print);

        Dialog dialog = new Dialog();
        dialog.setHeaderText("Le déterminat de la matrice " + lettre + " est : ");
        dialog.getDialogPane().setContent(vBox);
        dialog.setTitle("DÉTERMINANT");
        dialog.getDialogPane().getStylesheets().add("sample/style.css");
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("FERMER", ButtonBar.ButtonData.OK_DONE)
        );

        dialog.getDialogPane().setMinSize(200, 200);
        dialog.showAndWait();
    }

    public void inversion(int lignes, int colonnes, TextField[][] mat, TextField[] tab, String lettre){

        int[][] matInt = new int[lignes][colonnes];
        int[][] coefficient = new int[lignes][colonnes];
        int[][] inverse = new int[lignes][colonnes];
        boolean entier = caractere(tab, lignes, colonnes, lettre);

        for (int i = 0; i < lignes; i++){
            for (int j = 0; j < colonnes; j++){

                matInt[i][j] = Integer.parseInt(mat[i][j].getText());
            }
        }
        if (casesVidesSolo(tab, lignes*colonnes)){emptySolo();}
        else {
            if (entier == false){

                if (lignes == colonnes){  //pour calculer le déteminant, la matrice doit être carrée

                    int det =  calculDet(matInt);

                    if (det != 0){  //la matrice doit être régulière

                        for (int i = 0; i < lignes; i++){
                            for (int j = 0; j < colonnes; j++){

                                coefficient[i][j] = sousMatrice(lignes, colonnes, matInt, i, j);
                            }
                        }



                        for (int i = 0; i < lignes; i++){
                            for (int j = 0; j < colonnes; j++){   // les éléments sont toujours des fractions et java arrondie toujous à 0

                                inverse[i][j] = ((int) Math.pow(-1, i+j) * coefficient[i][j]) / det;
                            }
                        }

                        for (int i = 0; i < lignes; i++){
                            for (int j = 0; j < colonnes; j++){

                                inverse[i][j] = inverse[j][i];
                            }
                        }

                        repTranspo(inverse, lignes, colonnes); //revoir affichage
                    }
                    else {
                        Reguliere alert = new Reguliere();
                        alert.creationAlerte();
                    }
                }
                else {
                    Carrée alerte = new Carrée();
                    alerte.creationAlerte();
                }
            }
            else {
                Caractere alert = new Caractere();
                alert.creationAlerte();
            }
        }

            System.out.println(inverse[0][0]);

    }

    public int sousMatrice(int lignes, int colonne, int[][] matrice, int x, int y){

        int element = 0;
        int[][] sousMat = new int[matrice.length - 1][matrice[0].length - 1];

            for (int j = 0; j < matrice.length; j++) {
                for (int k = 0; k < matrice[0].length; k++) {
                    if (k != y && j != x) {

                        if (x < sousMat.length && y < sousMat.length){
                            sousMat[x][y] = matrice[j][k];
                        }
                        else {
                            sousMat[x+1][y-1] = matrice[j][k];  //arrayOutOfBound
                        }
                        y++;
                    }
                }

            }
            x++;
        element = calculDet(sousMat);
        return element;
    }
    public void inversionA(){inversion(Integer.parseInt(resultat1), Integer.parseInt(resultat2), tab2DA, tableau1, "A");}
    public void inversionB(){inversion(Integer.parseInt(resultat3), Integer.parseInt(resultat4), tab2DB, tableau2, "B");}

    public void multiplier() {//Condition pour l'addition : si A (m x n) et B (n x p)

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

            if (entier == false && entier2 == false){
                if (resultat2.equals(resultat3)){
                    if (casesVides()){ emptyDouble(); }
                    else {
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

                    }

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

        int produit = 0;
        int repC = 0;
        int[][] resultat = new int[lignes][colonnes];


        if (lignes == colonnes) {
            if (casesVidesSolo(tab, lignes*colonnes)){emptySolo();}
            else {
                boolean entier =  caractere(tab, lignes, colonnes, lettre);
                if (entier == false) {
                    if (validFactor(power) == false) {
                        power.setText("");
                    } else {
                        int exposant  = Integer.parseInt(power.getText());
                        if (exposant == 0) {
                            for (int i = 0; i < lignes; i++) {
                                for (int j = 0; j < colonnes; j++) {
                                    resultat[i][j] = 1;
                                }
                            }
                        }

                        if (exposant == 1) {
                            for (int i = 0; i < lignes; i++) {
                                for (int j = 0; j < colonnes; j++) {
                                    resultat[i][j] = Integer.parseInt(tableau[i][j].getText());
                                }
                            }
                        }

                        if (exposant > 1) {

                            int nbrMulti = 1;

                            while (nbrMulti < exposant) {

                                for (int i = 0; i < lignes; i++) { //nb de lignes

                                    for (int j = 0; j < colonnes; j++) {  // nb colonnes

                                        for (int k = 0; k < lignes; k++) {

                                            try {
                                                produit = Integer.parseInt(tableau[i][k].getText()) * Integer.parseInt(tableau[k][j].getText());
                                                repC = repC + produit;

                                            } catch (Exception e) {
                                                System.out.println("yolo");
                                            }
                                        }
                                        resultat[i][j] = resultat[i][j] + repC;
                                        repC = 0;
                                    }
                                }
                                nbrMulti++;
                            }
                        }
                        repMulti(resultat, "PUISSANCE");
                        power.setText("");
                    }
                }
            }
        }
        else{
            Carrée alert = new Carrée();
            alert.creationAlerte();
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

        boolean entier = caractere(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");
        boolean entier2 = caractere(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");

        if (entier == false && entier2 == false) {

            if (casesVides()) {
                emptyDouble();
            } else {

                // resultat dimensions mp x nq
                int m = Integer.parseInt(resultat1);                        // presque parfait, marche pas si 2X3 et 3X2
                int n = Integer.parseInt(resultat2);
                int p = Integer.parseInt(resultat3);
                int q = Integer.parseInt(resultat4);

                int[] etape1 = new int[p * q];

                int[][][] etape3 = new int[m * n][p][q];
                int[][][][] resultat = new int[m][n][p][q];

                for (int j = 0; j < m * n; j++) { //nb de valeur tabA
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
                    for (int k = 0; k < etape1.length; k++) {
                        etape2[k / q][k % q] = etape1[k];
                    }

                    //On prend ce tableau 2D et on le met dans un tableau de tableau 2D
                    etape3[j] = etape2;
                }

                // on retransforme ce tableau 1D en tableau 2D
                for (int r = 0; r < etape3.length; r++) {
                    //ajouter les valeurs au gridpane
                    resultat[r / n][r % n] = etape3[r];
                }

                GridPane reponse = new GridPane();


                for (int i = 0; i < m; i++) { //lignes
                    for (int j = 0; j < n; j++) { //colonnes
                        for (int k = 0; k < p; k++) { //colonnes total
                            for (int c = 0; c < q; c++) { //lignes total
                                //position  matA  lignes, colonnes   mat B lignes colonnes    coordonnées colonnes, lignes
                                reponse.add(new Label(String.valueOf(resultat[i][j][k][c])), c + (j * q), k + (i * p));


                            }
                        }

                    }

                }
                affichage(reponse, "PRODUIT TENSORIEL");

            }
        }
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
        alerte.setHeaderText("Vous devez remplir toutes les cases avant de pouvoir effectuer une opération sur cette matrice!");
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

    public boolean casesVidesSolo(TextField[] tableau, int nb){
        boolean vide = false;
        int nombre = nb;   //position
        for (int i = 0; i < nombre; i++) { //regarder s'il y a des cases vides
            if (tableau[i].getText().isEmpty()) { //Si oui, vide est vrai
                vide = true;
            }

        }
        return vide;
    }

    public void affichage(GridPane reponse, String operation){

        reponse.setAlignment(Pos.CENTER);
        reponse.setVgap(10); reponse.setHgap(13);

        for (Node r: reponse.getChildren()) {
            r.setScaleX(1.4); r.setScaleY(1.4); }


        Button print = new Button("IMPRIMER");
        print.setAlignment(Pos.BASELINE_CENTER);
        print.setMaxSize(100,18);
        print.setScaleX(1.1);print.setScaleY(1.1);

     

        Dialog dialog = new Dialog();

        VBox vBox = new VBox(reponse, print);

        vBox.setAlignment(Pos.CENTER);
        vBox.setFillWidth(true);
        vBox.setSpacing(50);


        dialog.setResizable(true);
        dialog.setTitle(operation);
        dialog.setHeaderText("Matrice Résultante : ");
        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getStylesheets().add("sample/style.css");

        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("FERMER", ButtonBar.ButtonData.OK_DONE)

        );
        dialog.getDialogPane().setMinSize(500, 500);
        print.setOnAction(event -> {
            imprimer(dialog);
        });
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

    public boolean validFactor(TextField nb){
        boolean mauvaisNb = true;
        for (int i = 0; i<nb.getText().length(); i++){
            if (nb.getText().charAt(i) < 48 || nb.getText().charAt(i)> 57 || nb.getText().equals("") ){
                mauvaisNb = false;
            }
        }
        if (mauvaisNb==false){
            Alert alerte = new Alert(Alert.AlertType.INFORMATION);
            alerte.setTitle("Information importante");
            alerte.setHeaderText("Le facteur multiplicatif doit être un nombre entier");
            alerte.setContentText(
                    "Veuillez corriger votre réponse! ");
            alerte.showAndWait();
            nb.setText("");
        }

        return mauvaisNb;
    }

    public void imprimer(Dialog dialog){
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(dialog.getOwner())){
            boolean success = job.printPage(dialog.getDialogPane());
            if (success) {
                job.endJob();
            }
        }


    }

    @FXML
    MenuItem importA;
    @FXML
    MenuItem importB;

    public void fileA(){ readFile("src/sample/donneesA.csv", matA, tab2DA, tableau2, matB, tab2DB); }
    public void fileB(){ readFile("src/sample/donneesB.csv", matB, tab2DB, tableau1, matA, tab2DA); }



    public void readFile(String path, GridPane matrice, TextField[][] tableau2D, TextField[] tabOther, GridPane matOther, TextField[][] tab2DOther ){
        matrice.getChildren().clear();
        int lignes = 0;
        int colonnes = 0;
        TextField[] tab = new TextField[1];
        try{
                List<String> list = Files.readAllLines(Paths.get(path));
                lignes = list.size();
                int compteur = -1;
                String[] nbColumn = list.get(0).split(",");
                colonnes = nbColumn.length;
                tab = new TextField[nbColumn.length*lignes];

                for (int i = 0; i < list.size(); i++) {
                    String[] parties = list.get(i).split(",");

                    for (int j = 0; j<parties.length; j++){
                        compteur++;
                        tab[compteur] = new TextField();
                        tab[compteur].setText(parties[j]);
                    }
                }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.print("Erreur tableau -1");
        }
        catch (IOException e){
            System.out.print("Erreur dans la lecture du fichier csv");
        }

        try {
            fichierCreation(tab.length, lignes, colonnes, tab, matrice, tableau2D );
            if (matOther.getChildren().isEmpty()){
                creation(9, 3, 3, tabOther, matOther, tab2DOther); }

        }catch (Exception e){ }

    }

    public void fichierCreation(int nbTextField, int nbLignes, int nbColonnes, TextField[] tableau, GridPane matrice, TextField[][] tableau2D) {

        for (int i = 0; i < nbTextField; i++) {
            int col = i % (nbColonnes);
            int row = (i / nbColonnes);
            matrice.add(tableau[i], col, row);
        }
        //Creation des tableaux 2D
        int k = 0;
        for (int i = 0; i < nbLignes; i++) {

            for (int j = 0; j < nbColonnes; j++) {

                tableau2D[i][j] = tableau[k];
                k++;
            }
        }
    }

}
