package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
        Faire une classe matrice qui est un tableau
        faire une classe matriceView qui elle hérite de GRIDPANE
        listener et bind bidirectional ou juste bind la position du gridpane avec la position du tableau
         */

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Calculatrice Matricielle");
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 300, 275));


        primaryStage.show();
    }

}
