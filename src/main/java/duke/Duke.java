package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(LOCATION);
        tasks = new TaskList(storage.loadData());
    }

    /**
     * Responds to user input.
     *
     * @param input Message input by the user.
     * @return String of the response.
     */
    public String getResponse(String input) {
        parser = new Parser(tasks, storage);
        String response = parser.parseCommand(input);
        if (response == null) {
            return "I cannot understand what you are saying!";
        } else {
            assert !response.equals("");
            return response;
        }
    }
}
