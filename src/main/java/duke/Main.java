/**
 * This function starts the program and creates the window.  A GUI for Duke using FXML.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke;

import java.io.IOException;

import duke.exceptions.DukeDataLoadException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private final Duke duke = new Duke();

    /**
     * Constructor for Main.
     *
     * @throws IOException If there are errors processing the file.
     */
    public Main() throws IOException, DukeDataLoadException {
    }

    /**
     * Starts the GUI.
     *
     * @param stage The stage where elements of the GUI are situated.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(new Image("/images/DaUser.png"));
            stage.setTitle("Notaro");
            fxmlLoader.<duke.ui.MainWindow>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
