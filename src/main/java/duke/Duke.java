package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Duke class
 * Uses storage class to gather data from text file
 * When run, parses input into parser class which utilizes ui class to give an output
 * After each command it saves to the storage text file
 */
public class Duke extends Application {

    public Duke() {
    }

    /**
     * Start function for duke GUI
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Duke.class.getResource("/duke.fxml"));
        Scene scene = new Scene(root); // Setting the scene to be our Label

        stage.setTitle("Duke Bot");
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
