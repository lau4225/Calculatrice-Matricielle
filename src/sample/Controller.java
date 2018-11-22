package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    /*
    4/3 = ligne
    4%3 = colone
     */


    public void dialog(){
        TextInputDialog ligneA = new TextInputDialog();
        ligneA.setTitle("Matrice A");
        ligneA.setContentText("Entrez le nombre de lignes");
        resultat1 = ligneA.showAndWait().get();

        TextInputDialog colonneA = new TextInputDialog();
        colonneA.setTitle("Matrice A");
        colonneA.setContentText("Entrez le nombre de colonnes");
        resultat2 = colonneA.showAndWait().get();

        TextInputDialog ligneB = new TextInputDialog();
        ligneB.setTitle("Matrice B");
        ligneB.setContentText("Entrez le nombre de lignes");
        resultat3 = ligneB.showAndWait().get();

        TextInputDialog colonneB = new TextInputDialog();
        colonneB.setTitle("Matrice B");
        colonneB.setContentText("Entrez le nombre de colonnes");
        resultat4 = colonneB.showAndWait().get();

        int nbTextA = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
        int nbTextB = Integer.parseInt(resultat3)*Integer.parseInt(resultat4);

        try {
            creation(nbTextA, Integer.parseInt(resultat1), Integer.parseInt(resultat2), tableau1, matA);
            creation(nbTextB, Integer.parseInt(resultat3), Integer.parseInt(resultat4), tableau2, matB);
        }catch (Exception e){

        }


    }

    public void creation(int nbTextField, int nbLignes, int nbColonnes, TextField[] tableau, GridPane matrice){

        for (int i = 0; i< nbTextField; i++){
            tableau[i] = new TextField();

            matrice.add(tableau[i], i%(nbColonnes), (i/nbLignes));
        }
    }


    @FXML
    Button somme;
    public void addition(){

        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {
            int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
            int[] resultat = new int[nombre];

                for (int i = 0; i < nombre; i++) {

                    try {
                        resultat[i] = Integer.parseInt(tableau1[i].getText()) + Integer.parseInt(tableau2[i].getText());
                    }catch (NumberFormatException e){
                                                                                    //dialog à faire si aucun nombre d'entré dans une case
                    }
                }
                reponse(resultat);
        }
        else {
                                                // dialog à faire si les dimensions ne concordent pas
        }

    }
    @FXML
    Button difference;
    public void soustraction(){
        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {
            //Si les dimensions sont pareils
            int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
            int[] resultat = new int[nombre];

            for (int i = 0; i < nombre; i++) {

                try {
                    resultat[i] = Integer.parseInt(tableau1[i].getText()) - Integer.parseInt(tableau2[i].getText());
                }catch (NumberFormatException e){
                                                                        //dialog à faire si aucun nombre d'entré dans une case
                }
            }
            reponse(resultat);
        }
        else {
                                                                // dialog à faire si les dimensions ne concordent pas
        }
    }

    @FXML
    Button hadam;
    public void hadamard(){
        if (resultat1.equals(resultat3) && resultat2.equals(resultat4)) {
            //Si les dimensions sont pareils
            int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat2);
            int[] resultat = new int[nombre];

            for (int i = 0; i < nombre; i++) {

                try {
                    resultat[i] = Integer.parseInt(tableau1[i].getText()) * Integer.parseInt(tableau2[i].getText());
                }catch (NumberFormatException e){
                    //dialog à faire si aucun nombre d'entré dans une case
                }
            }
            reponse(resultat);
        }
        else {
            // dialog à faire si les dimensions ne concordent pas
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
    public void multiScalaire(){                    //probleme : affiche les 2 en même temps...  code pareil...
        int[] corolaireA = new int[Integer.parseInt(resultat1)* Integer.parseInt(resultat2)];

        if (!produitNbA.getText().isEmpty()){ //Si la case n'est pas vide
            for (int i = 0; i<corolaireA.length; i++){
                corolaireA[i] = Integer.parseInt(tableau1[i].getText()) * Integer.parseInt(produitNbA.getText());
            }
            solo(corolaireA, Integer.parseInt(resultat1), Integer.parseInt(resultat2));
            produitNbA.setText("");
        }

        int[] corolaireB = new int[Integer.parseInt(resultat3)* Integer.parseInt(resultat4)];
        if (!produitNbB.getText().isEmpty()){ //Si la case n'est pas vide
            for (int i = 0; i<corolaireB.length; i++){
                corolaireB[i] = Integer.parseInt(tableau2[i].getText()) * Integer.parseInt(produitNbB.getText());
            }
            solo(corolaireB, Integer.parseInt(resultat3), Integer.parseInt(resultat4));
            produitNbB.setText("");
        }



    }


    public void multiplier(){//Condition pour l'addition : si A (m x n) et B (n x p)
        int nombre = Integer.parseInt(resultat1)*Integer.parseInt(resultat4);
        int[] resultat = new int[nombre];

        if (resultat2.equals(resultat3)){



        }else {
                                                 // dialog à faire si les dimensions ne concordent pas
        }
    }


    public void reponse(int[] corolaire){            // à embellir plus tard

        GridPane reponse = new GridPane();

        for (int i = 0; i< corolaire.length; i++){

            reponse.add(new Label(String.valueOf(corolaire[i])), i%Integer.parseInt(resultat2), (int) (i/ Integer.parseInt(resultat3)));
        }

        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );
        dialog.showAndWait();

    }

    public void solo(int[] corolaire, int lignes, int colonnes){            // à embellir plus tard aussi

        GridPane reponse = new GridPane();

        for (int i = 0; i< corolaire.length; i++){
            //ajouter les valeurs au gridpane
            reponse.add(new Label(String.valueOf(corolaire[i])), i%colonnes, (int) (i/lignes));
        }

        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(reponse);
        dialog.getDialogPane().getButtonTypes().add(
                new ButtonType("Clear", ButtonBar.ButtonData.OK_DONE)
        );

        dialog.showAndWait();

    }

}
