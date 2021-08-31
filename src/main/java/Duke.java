import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke is a personal assistant chatbot that helps keep track of various tasks.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Duke extends Application {

    /** Stores location of the stored tasks. */
    private static final String LOCATION = "./data/duke.txt";
    /** Stores the tasks in a TaskList object. */
    private TaskList tasks;
    /** Keeps track of the tasks stored. */
    private Storage storage;
    /** Parses the user inputs. */
    private Parser parser;
    /** Deals with making sense of the user commands. */
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath File path to load, write and store data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadData());
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(LOCATION);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Starts the Duke program.
     */
    public void run() {
        boolean isRunning = true;
        ui.intro();
        ui.greet();
        parser = new Parser(tasks, storage);
        while (isRunning) {
            isRunning = parser.parseCommand();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke(LOCATION).run();
    }
}
