import duke.TaskList;
import duke.UI;
import duke.Storage;
import duke.DukeException;
import duke.Command;
import duke.Parser;

/**
 * Duke Class to read input commands and accordingly create a list of task.
 */
public class Duke {

    private final TaskList tasks;
    private final UI ui;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor for the Duke class
     */
    public Duke() {

        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage.load());
        ui = new UI();
        parser = new Parser();
    }

    /**
     * Method to Run the Duke class
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(ui, tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main Method where the Duke execution begins
     * @param args Argument for the Main Method
     */
    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}