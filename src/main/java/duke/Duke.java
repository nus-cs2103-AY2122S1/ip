package duke;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The entry point to the Duke chatbot.
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Default constructor for GUI Launcher.
     */
    public Duke() {
    }

    /**
     * Overloaded constructor for Duke.
     *
     * @param filePath for storing tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
            ui.greetWithFamiliarity(taskList);
        } catch (DukeException e) {
            ui.showDukeException(e.getMessage());
            storage.resetTasks();

            if (taskList != null) {
                taskList.clearTasks();
            }
        } finally {
            parser = new Parser(storage, ui, taskList);
        }
    }

//    /**
//     * Main method to start Duke
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        String filePath = System.getProperty("user.dir") + File.separator + "tasks.txt";
//        new Duke(filePath).run();
//    }

    /**
     * Starts the assistant.
     */
    public void run() {
        String input = ui.getNextInput();

        while (true) {
            try {
                boolean keepParsing = parser.parse(input);
                if (!keepParsing) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = ui.getNextInput();
        }
        ui.closeInput();
    }

    /**
     * Start the Duke GUI.
     *
     * @param stage to be displayed
     */
    @Override
    public void start(Stage stage) {
        ui = new Ui(stage);
        ui.start();
    }


}
