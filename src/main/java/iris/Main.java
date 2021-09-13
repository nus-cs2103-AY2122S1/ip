package iris;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Iris using FXML.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class Main extends Application {

    private final Iris iris = new Iris();

    /**
     * This method is called after the system is loaded.
     *
     * @param stage The stage to set the scene.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Iris Bot");
            fxmlLoader.<MainWindow>getController().setIris(iris);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
