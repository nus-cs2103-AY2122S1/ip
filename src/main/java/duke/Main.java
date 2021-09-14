package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();


    /**
     * A public constructor for Main class.
     * @throws IOException Thrown when storage file from dukec is not found.
     */
    public Main() throws IOException {
    }

    /**
     * Overrides the stage method in Application. Sets the stage for the GUI.
     * @param stage Stage for Duke GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Duke.jpg")));
            stage.setTitle("DukePro");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
