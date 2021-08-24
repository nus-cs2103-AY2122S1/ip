import duke.command.Command;
import duke.error.DukeException;
import duke.general.Parser;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Chatbot that helps you form a task list
 */

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the Duke chatbot program
     * @param filePath File path to save the data files of the Tasklist to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new Tasklist(storage.loadSave());
        parser = new Parser();
    }

    /**
     * Run the main program
     */
    public void run() {
        ui.showWelcome();
        while (ui.getLoop()) {
            try {
                String fullCommand = ui.readInput();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
