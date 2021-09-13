package duke;

import javafxgui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.stage.Stage;


/**
 * A Class to initialize Duke Object and
 * run the entire program.
 * @author Jia Rong
 */
public class LaunchDuke extends Application {
    /**
     * Specifies the relative path of where to
     * write user stored tasks to.
     */
    private static final String PATH = "./data/duke.txt";

    /**
     * Initializes a new duke to kickstart the
     * run of the program.
     */
    private Duke duke = new Duke(PATH);

    /**
     * Loads the FXML for display of GUI.
     * @param stage displays tasks to user
     * via the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    LaunchDuke.
                            class.
                            getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}