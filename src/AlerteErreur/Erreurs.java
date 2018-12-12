package AlerteErreur;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Erreurs extends Application {

    private String headerText;
    private String contentText;


    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.show();
    }

    public ButtonType creationAlerte(){

        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Erreurs");
        alerte.setHeaderText(headerText);
        alerte.setContentText(contentText);
        return alerte.showAndWait().get();

    }
}
