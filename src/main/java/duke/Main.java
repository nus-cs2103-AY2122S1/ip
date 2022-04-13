package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class encapsulates the main class that starts Duke with a GUI.
 */
public class Main extends Application {

    private Duke currDuke = Duke.getCurrDuke();


    @Override
    public void start(Stage stage) {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("The Seagull Tasker");
            Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ROTS.png"));
            stage.getIcons().add(dukeImage);

            scene.getRoot().setStyle("-fx-font-family: 'Courier New'");

            fxmlLoader.<MainWindow>getController().setDuke(currDuke);
            stage.show();
            Duke.dukeStarter(currDuke);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
