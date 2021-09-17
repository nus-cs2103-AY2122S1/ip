package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class Duke extends Application {
    private static Parser parser;
    private static Storage storage;
    /**
     * Creates Duke object.
     *
     * @param fileName name of the file that stores all the tasks.
     */
    public Duke(String fileName) {
        storage = new Storage(fileName);
        try {
            storage.loadTasks();
        } catch (IOException e) {
            Ui.showLoadingError();
        }
        parser = new Parser(storage.getTasks(), storage);
    }
    /**
     * Starts the program.
     *
     * @param stage the UI of the program.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public String[] getResponse(String input) {
        String[] results = parser.parseInput(input);
        return results;
    }
}
