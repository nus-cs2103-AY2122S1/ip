import duke.parser.Parser;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.lang.String;

/**
 * Duke is a personal assistant chatbot that helps keep track of various tasks.
 *
 * @author: James Kua
 * @version: Duke-Level-8
 */
public class Duke {

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

    public static void main(String[] args) {
        new Duke(LOCATION).run();
    }
}
