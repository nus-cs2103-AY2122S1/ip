import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * Represents the core of Duke.
 */
public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Represents a new Duke project.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui, storage);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Starts the program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            isExit = parser.isExit(fullCommand);
            if (!isExit) {
                parser.parse(fullCommand);
            }
        }
        ui.showGoodBye();
    }

    public static void main(String[] args) { // new Duke("data/tasks.txt").run();
        new Duke("../../../../data/tasks.txt").run();
    }
}
