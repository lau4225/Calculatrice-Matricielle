package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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


    /*
    4/3 = ligne
    4%3 = colone
     */


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
        }catch (Exception e){

        }


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
                reponse(resultat);
            }

        }
        else {
             wrongDimensions();
        }

    }
    @FXML
    Button difference;
    public void soustraction(){

        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {

            if (casesVides()){ emptyDouble(); }
            else {
                int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);
                int[] resultat = new int[nombre];
                for (int i = 0; i < nombre; i++) {
                    try {
                        resultat[i] = Integer.parseInt(tableau1[i].getText()) - Integer.parseInt(tableau2[i].getText());
                    }catch (Exception e){ emptyDouble(); } }
                reponse(resultat);
            }

        }
        else {
            wrongDimensions();
        }
    }

    @FXML
    Button hadam;
    public void hadamard(){
        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {

            if (casesVides()){ emptyDouble(); }
            else {
                int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);
                int[] resultat = new int[nombre];
                for (int i = 0; i < nombre; i++) {
                    try {
                        resultat[i] = Integer.parseInt(tableau1[i].getText()) * Integer.parseInt(tableau2[i].getText());
                    }catch (Exception e){ emptyDouble(); } }
                reponse(resultat);
            }

        }
        else {
            wrongDimensions();
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


    public void multiScalaire(TextField[] tableau, int lignes, int colonnes, TextField facteur) {
        int[] corolaire = new int[lignes * colonnes];
        boolean vide = false;

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
                solo(corolaire, colonnes);
                facteur.setText("");
            }
        }
    }

    public void multiScalaireA() { multiScalaire(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), produitNbA); }
    public void multiScalaireB() { multiScalaire(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), produitNbB); }

    @FXML
    Button transpoA;
    @FXML
    Button transpoB;
    public void transposer(TextField[][] tableau, int lignes, int colonnes){                 //laurie rendue ici mais ca marche pas


        if (lignes == colonnes) {
            int[][] resultat = new int[lignes][colonnes];

            for (int i = 0; i < lignes; i++) {

                for (int j = 0; j < colonnes; j++) {
                    resultat[i][j] = Integer.parseInt(tableau[j][i].getText());

                }

            }

            repMulti(resultat);

        }else {
                                                                                // dialog
        }
    }

    public void transposerA(){ transposer(tab2DA, Integer.parseInt(resultat1), Integer.parseInt(resultat2));}
    public void transposerB(){ transposer(tab2DB, Integer.parseInt(resultat3), Integer.parseInt(resultat4));}



    @FXML
    Button detA;
    @FXML
    Button detB;

    public void determinant(TextField[] tableau, int lignes, int colonnes, String lettre){//Laurie rendu ici


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

                default : det = 10; repDeterminant(det, lettre);                    //À FAIRE POUR DES MATRICES D'ORDRE SUPÉRIEUR A 3
            }
        }else{
                                                                                            //dialog que la matrice doit être carré
        }
    }

    public void determinantA(){determinant(tableau1, Integer.parseInt(resultat1), Integer.parseInt(resultat2), "A");}
    public void determinantB(){determinant(tableau2, Integer.parseInt(resultat3), Integer.parseInt(resultat4), "B");}


    public void repDeterminant(int determinant, String lettre){
        Label reponse = new Label(Integer.toString(determinant));

        Dialog dialog = new Dialog();
        dialog.setHeight(500); dialog.setWidth(800);
        dialog.setHeaderText("Le déterminat de la matrice " + lettre + " est : ");
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );
        dialog.showAndWait();
    }


    public void multiplier(){//Condition pour l'addition : si A (m x n) et B (n x p)
        if (resultat2.equals(resultat3)){
            int[][] resultat = new int[Integer.parseInt(resultat1)][Integer.parseInt(resultat4)];

            int repC =0;
            int produit = 0;


            for (int i = 0; i < Integer.parseInt(resultat1); i++) { //nb de lignes

                for (int j = 0; j < Integer.parseInt(resultat4); j++) {  // nb colonnes

                    for (int k = 0; k < Integer.parseInt(resultat2); k++) {  //represente k dans la definition
                        try {


                            produit = Integer.parseInt(tab2DA[i][k].getText())*Integer.parseInt(tab2DB[k][j].getText());
                            repC = repC + produit;

                        } catch (Exception e) {
                            System.out.println("yolo");
                        }
                    }

                    resultat[i][j] = repC;
                    repC =0;

                }

            }

            repMulti(resultat);
        }else { wrongDimensions(); }
    }

    @FXML
    TextField expoB;
    @FXML
    TextField expoA;

    public void puissance(TextField power, int lignes, int colonnes, TextField[][] tableau){ //doit être carrée

        int produit = 0;
        int repC = 0;
        int[][] resultat = new int[lignes][colonnes];
        int exposant  = Integer.parseInt(power.getText());

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

        }
        repMulti(resultat);

    }
    public void puissanceA(){puissance(expoA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tab2DA);}
    public void puissanceB(){puissance(expoB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tab2DB);}

    public void produitVectoriel(){                                                             // rendu ici exceptions

        int[] resultat = new int[3];

        if (Integer.parseInt(resultat2) ==  3 && Integer.parseInt(resultat4) == 3){

            for(int i = 0; i < resultat.length; i++){

                resultat[0] = (Integer.parseInt(tableau1[1].getText())*Integer.parseInt(tableau2[2].getText())) -
                        (Integer.parseInt(tableau1[2].getText())*Integer.parseInt(tableau2[1].getText()));

                resultat[1] = (Integer.parseInt(tableau1[2].getText())*Integer.parseInt(tableau2[0].getText())) -
                        (Integer.parseInt(tableau1[0].getText())*Integer.parseInt(tableau2[2].getText()));

                resultat[2] = (Integer.parseInt(tableau1[0].getText())*Integer.parseInt(tableau2[1].getText())) -
                        (Integer.parseInt(tableau1[1].getText())*Integer.parseInt(tableau2[0].getText()));
            }

            reponse(resultat);
        }
        else {
                                                                            // idem
        }
    }
    public void repMulti(int[][] corolaire){            // à embellir plus tard

        GridPane reponse = new GridPane();

        for (int i = 0; i< Integer.parseInt(resultat4); i++){
            for (int j =0; j < Integer.parseInt(resultat1); j++){
                reponse.add(new Label(String.valueOf(corolaire[j][i])), i, j);

            }
        }

        reponse.setAlignment(Pos.TOP_CENTER);
        reponse.setVgap(10); reponse.setHgap(10);

        for (Node r: reponse.getChildren()) {
            r.setScaleX(1.2); r.setScaleY(1.2);
        }

        Dialog dialog = new Dialog();
        dialog.setHeight(500); dialog.setWidth(800);
        dialog.setHeaderText("Matrice Résultante : ");
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );
        dialog.showAndWait();

    }



        //réponse d'une opération sur deux matrices
    public void reponse(int[] corolaire){                                                       // à embellir plus tard

        GridPane reponse = new GridPane();

        for (int i = 0; i< corolaire.length; i++){

            reponse.add(new Label(String.valueOf(corolaire[i])), i%Integer.parseInt(resultat2), (int) (i/ Integer.parseInt(resultat2)));
        }

        reponse.setAlignment(Pos.TOP_CENTER);
        reponse.setVgap(10); reponse.setHgap(10);

        for (Node r: reponse.getChildren()) {
            r.setScaleX(1.2); r.setScaleY(1.2);
        }

        Dialog dialog = new Dialog();
        dialog.setHeight(500); dialog.setWidth(800);
        dialog.setHeaderText("Matrice Résultante : ");
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );
        dialog.showAndWait();

    }

        //reponse d'une opération sur une seule matrice
    public void solo(int[] corolaire, int colonnes){     // donne la reponse à embellir plus tard aussi

        GridPane reponse = new GridPane();

        for (int i = 0; i< corolaire.length; i++){
            //ajouter les valeurs au gridpane
            reponse.add(new Label(String.valueOf(corolaire[i])), i%colonnes, (int) (i/colonnes));
        }

        reponse.setAlignment(Pos.TOP_CENTER);
        reponse.setVgap(10); reponse.setHgap(10);

        for (Node r: reponse.getChildren()) {
            r.setScaleX(1.2); r.setScaleY(1.2);
        }


        Dialog dialog = new Dialog();
        dialog.setHeight(500); dialog.setWidth(800);
        dialog.setHeaderText("Matrice Résultante : ");
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );

        dialog.showAndWait();

    }

    public void wrongDimensions(){ //message d'erreur qui va afficher

        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Information importante");
        alerte.setHeaderText("Les dimensions des matrices A et B ne concordent pas!");
        alerte.setContentText(
                "Vous pouvez effectuer une autre opération ou bien cliquer sur démarrer");
        alerte.showAndWait();
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

        int nombre = Integer.parseInt(resultat1) * Integer.parseInt(resultat2);
        for (int i = 0; i < nombre; i++) { //regarder s'il y a des cases vides
            if (tableau1[i].getText().isEmpty() || tableau2[i].getText().isEmpty()) { //Si oui, vide est vrai
                vide = true;
            }

        }
        return vide;
    }



}
