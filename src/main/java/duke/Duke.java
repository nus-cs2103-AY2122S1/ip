package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;


/**
 * A chatbot based on Project Duke
 * @author KelvinSoo
 * @version Level-10
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final String FILE_PATH = "./data/duke.txt";

    /**
     * A constructor to initialize a chatbot.
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
    }

    /**
     * Start the duke chat bot.
     * @throws DukeException The start up errors.
     */
    public void start() throws DukeException{
        taskList = new TaskList(storage.load());
    }

    /**
     * Get a response from a user input.
     * @param input The user input.
     * @return The response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
