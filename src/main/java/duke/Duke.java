package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.NoSuchCommandException;
import duke.exceptions.NoSuchTaskException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main Class that calls the other components to put the whole system together
 */

public class Duke extends Application {

    private Storage storage;
    private Tasklist tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     *
     * @param filePath file path to save the tasklist to
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.fileToTasklist();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    /**
     * Logic for getting Duke's Response
     */

    public String getResponse(String input) {

        try {
            Command command = Parser.parse(input);
            String result = command.execute(tasks, ui, storage);
            return result;
        } catch (NoSuchCommandException | NoSuchTaskException | IOException ex) {
            return "Something went wrong: \n" + ex.getMessage();
        }
    };
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Main Method that creates new Duke and runs it
     */

}

