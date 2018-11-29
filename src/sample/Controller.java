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

    private TextField[] tableau1 = new TextField[1000];
    private TextField[] tableau2 = new TextField[1000];

    private String resultat1 = "";
    private String resultat2 = "";
    private String resultat3 = "";
    private String resultat4 = "";

    private TextField[][] tab2DA = new TextField[1000][1000];
    private TextField[][] tab2DB = new TextField[1000][1000];


    /*
    4/3 = ligne
    4%3 = colone
     */


    public void dialog(){
        matA.getChildren().clear();
        matB.getChildren().clear();

        TextInputDialog ligneA = new TextInputDialog();
        ligneA.setTitle("Matrice A");
        ligneA.setHeaderText("Création");
        ligneA.setContentText("Entrez le nombre de lignes");
        resultat1 = ligneA.showAndWait().get();
        if (resultat1.equals("")){
            ligneA.setHeaderText("Vous n'avez pas entré un nombre!");
            resultat1 = ligneA.showAndWait().get();
        }

        TextInputDialog colonneA = new TextInputDialog();
        colonneA.setTitle("Matrice A");
        colonneA.setHeaderText("Création");
        colonneA.setContentText("Entrez le nombre de colonnes");
        resultat2 = colonneA.showAndWait().get();
        if (resultat2.equals("")){
            colonneA.setHeaderText("Vous n'avez pas entré un nombre!");
            resultat2 = colonneA.showAndWait().get();
        }


        TextInputDialog ligneB = new TextInputDialog();
        ligneB.setTitle("Matrice B");
        ligneB.setHeaderText("Création");
        ligneB.setContentText("Entrez le nombre de lignes");
        resultat3 = ligneB.showAndWait().get();
        if (resultat3.equals("")){
            ligneB.setHeaderText("Vous n'avez pas entré un nombre!");
            resultat3 = ligneB.showAndWait().get();
        }


        TextInputDialog colonneB = new TextInputDialog();
        colonneB.setTitle("Matrice B");
        colonneB.setHeaderText("Création");
        colonneB.setContentText("Entrez le nombre de colonnes");
        resultat4 = colonneB.showAndWait().get();
        if (resultat4.equals("")){
            colonneB.setHeaderText("Vous n'avez pas entré un nombre!");
            resultat4 = colonneB.showAndWait().get();
        }


        int nbTextA = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
        int nbTextB = Integer.parseInt(resultat3)*Integer.parseInt(resultat4);

        try {
            creation(nbTextA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tableau1, matA, tab2DA);
            creation(nbTextB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tableau2, matB, tab2DB);
        }catch (Exception e){

        }


    }

    public void creation(int nbTextField, int nbLignes, int nbColonnes, TextField[] tableau, GridPane matrice, TextField[][] tableau2D){

        for (int i = 0; i< nbTextField; i++){

                tableau[i] = new TextField();
                int col = i%(nbColonnes);
                int row = (i/nbColonnes);
                matrice.add(tableau[i], col, row);
        }

        for (int i = 0; i < nbLignes; i++){
            for (int j = 0; j < nbColonnes; j++){

                tableau2D[i][j] = tableau[i];
            }
        }

    }


    @FXML
    Button somme;
    public void addition(){

        boolean vide = false;

        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {
            int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
            int[] resultat = new int[nombre];

            for (int i = 0; i < nombre; i++) { //regarder s'il y a des cases vides
                if(tableau1[i].getText().isEmpty()|| tableau2[i].getText().isEmpty()){ //Si oui, vide est vrai
                     vide = true;
            }}

            if (vide==true){ emptyDouble(); }
            else {
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

        boolean vide = false;
        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {
            int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
            int[] resultat = new int[nombre];

            for (int i = 0; i < nombre; i++) { //regarder s'il y a des cases vides
                if(tableau1[i].getText().isEmpty()|| tableau2[i].getText().isEmpty()){ //Si oui, vide est vrai
                    vide = true;
                }}

            if (vide==true){ emptyDouble(); }
            else {
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
        boolean vide = false;
        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {
            int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
            int[] resultat = new int[nombre];

            for (int i = 0; i < nombre; i++) { //regarder s'il y a des cases vides
                if(tableau1[i].getText().isEmpty()|| tableau2[i].getText().isEmpty()){ //Si oui, vide est vrai
                    vide = true;
                }}

            if (vide==true){ emptyDouble(); }
            else {
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
    public void multiScalaire() {
        int[] corolaireA = new int[Integer.parseInt(resultat1) * Integer.parseInt(resultat2)];
        boolean videA = false;

        if (!produitNbA.getText().isEmpty()) { //Si la case n'est pas vide

            for (int i = 0; i < corolaireA.length; i++) { //regarder s'il y a des cases vides
                if(tableau1[i].getText().isEmpty()){ //Si oui, vide est vrai
                    videA = true;
                }}

            if (videA){ emptySolo(); }
            else {
                for (int i = 0; i < corolaireA.length; i++) {
                    try {
                            corolaireA[i] = Integer.parseInt(tableau1[i].getText()) * Integer.parseInt(produitNbA.getText());
                    } catch (Exception e) {
                        emptySolo();
                    }

                }
                solo(corolaireA, Integer.parseInt(resultat2));
                produitNbA.setText("");
            }
        }


        int[] corolaireB = new int[Integer.parseInt(resultat3) * Integer.parseInt(resultat4)];
        boolean videB = false;
        if (!produitNbB.getText().isEmpty()) { //Si la case n'est pas vide

            for (int i = 0; i < corolaireB.length; i++) { //regarder s'il y a des cases vides
                if(tableau2[i].getText().isEmpty()){ //Si oui, vide est vrai
                    videB = true;
                }}

            if (videB){ emptySolo(); }
            else {
                for (int i = 0; i < corolaireB.length; i++) {
                    try {
                        corolaireB[i] = Integer.parseInt(tableau2[i].getText()) * Integer.parseInt(produitNbB.getText());
                    } catch (Exception e) {
                        emptySolo();
                    }

                }
                solo(corolaireB, Integer.parseInt(resultat4));
                produitNbB.setText("");
            }
        }
    }


    public void multiplier(){//Condition pour l'addition : si A (m x n) et B (n x p)

        int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat4);
        int[][] resultat = new int[nombre][nombre];

        if (resultat2.equals(resultat3)){

            int repC =0;
            int produit = 0;

            for (int i = 0; i < Integer.parseInt(resultat4); i++){

                for (int j = 0; j < Integer.parseInt(resultat1); j++){

                    for (int k = 0; k < Integer.parseInt(resultat2); k++){
                        try{

                            produit = Integer.parseInt(tab2DA[j][k].getText())*Integer.parseInt(tab2DB[k][i].getText());
                            repC = repC + produit;

                        }
                        catch (NumberFormatException e){
                            System.out.println("yolo");
                        }
                        catch (NullPointerException e){
                            System.out.println("yolo2");
                        }
                    }
                    resultat[j][i] = repC;
                    repC =0;
                    //produit =0;

                }
            }

            System.out.println(tab2DA[0][0].getText());

            repMulti(resultat);



        }else { wrongDimensions(); }
    }

    public void produitVectoriel(){                                                        // rendu ici exceptions

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



    public void reponse(int[] corolaire){            // à embellir plus tard

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

    public void solo(int[] corolaire, int colonnes){            // à embellir plus tard aussi

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



}
