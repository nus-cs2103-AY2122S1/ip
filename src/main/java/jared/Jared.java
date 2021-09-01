package jared;

import jared.common.DukeException;
import jared.storage.Storage;
import jared.task.TaskList;
import jared.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class for the Jared project. Allows user to create a task list to keep track of Todos, Deadlines and Events.
 */
public class Jared extends Application {
    private static final String FILE_NAME = "data.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Jared Class.
     */
    public Jared() {
        ui = new Ui();
        storage = new Storage(FILE_NAME);
        try {
            tasks = new TaskList(storage.loadData(), storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the Jared instance. Starts up the scanner to receive tasks.
     */
    public void run() {
        try {
            storage.loadData();
            ui.runScanner(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Main function.
     */
    public static void main(String[] args) {
        new Jared().run();
    }

}

