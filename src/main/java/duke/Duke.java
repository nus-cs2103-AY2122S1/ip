package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Contains main() method for Duke chatbot for IP of CS2103 2021.
 *
 * author: Ren Weilin A0216723J
 */
public class Duke extends Application {

    private final UI userInterface;
    private final TaskList taskList;

    /**
     * Initializes the necessary components for Duke to function.
     */
    public Duke() {
        Parser parser = new Parser();
        taskList = new TaskList();
        Storage storage = new Storage(taskList);
        userInterface = new UI(parser, storage, taskList);
    }

    /**
     * Starts Duke chatbot.
     *
     * @param args CMD arguments.
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        try {
            dukeInstance.userInterface.start();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            dukeInstance.userInterface.start();
        }
    }

    /**
     * Enters
     *
     * @param stage The stage for Duke UI.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

}
