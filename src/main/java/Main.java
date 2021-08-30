import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Iris using FXML.
 */
public class Main extends Application {

    private IrisApplication irisApplication = new IrisApplication();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("styles/application.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setIris(irisApplication);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
