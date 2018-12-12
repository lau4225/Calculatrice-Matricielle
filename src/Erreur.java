import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Erreur extends Application {

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


        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Erreur");
        alerte.setHeaderText(headerText);
        alerte.setContentText(contentText);
        alerte.showAndWait();
    }
}
