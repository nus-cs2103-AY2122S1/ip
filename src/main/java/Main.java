import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import iris.Iris;
/**
 * Represents the GUI Application for Iris
 */
public class Main extends Application {

    private Iris iris = new Iris();

    /**
     * Starts the GUI Application for Iris
     *
     * @param stage  JavaFX stage for Application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Iris");
            fxmlLoader.<MainWindow>getController().setIris(iris);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
