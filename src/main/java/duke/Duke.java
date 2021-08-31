package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Main class. Starts the program.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    public Duke() { }

    /**
     * Constructor.
     *
     * @param filePath filepath to load tasks from
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to start running the program and start taking user input.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        Parser myParser = new Parser();

        Ui.showWelcomeMessage();

        boolean checker = true;
        while (checker) {
            checker = myParser.scanInputs(scan, tasks, true);
        }

        // Save the tasks in duke.txt
        storage.saveToFile(tasks.getTasks());
        Ui.showTasksSaved();
    }

    /**
     * Main method, pretty self-explanatory.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
