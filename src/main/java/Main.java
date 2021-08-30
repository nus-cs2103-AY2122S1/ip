import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.duke.Duke;

public class Main extends Application {
    /**
     * Runs the main Duke ToDo Application
     * 
     * @param args
     * @throws FileNotFoundException when file is not found in the given location.
     * @throws IOException           when Scanner is not able to scan anything from
     *                               the given file path.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Duke duke = new Duke();
        duke.start();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
