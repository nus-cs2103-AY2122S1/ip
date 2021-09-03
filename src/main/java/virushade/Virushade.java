package virushade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import virushade.tasks.TaskList;

/**
 * The Main class of the application.
 */
public class Virushade extends Application {
    private Storage storageFile;
    private TaskList taskList;

    /**
     * The constructor for Virushade.
     * @param fileName The file path of the file where data is written to.
     */
    public Virushade(String fileName) {
        storageFile = new Storage(fileName);
        try {
            TaskList taskList = new TaskList(storageFile);
        } catch (VirushadeException e) {
            Ui.handleVirushadeException(e);
        }
    }

    /**
     * The running sequence for Virushade.
     */
    public void run() {
        Ui.interact();
    }

    /**
     * Starts the Virushade application.
     * @param stage The stage.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
