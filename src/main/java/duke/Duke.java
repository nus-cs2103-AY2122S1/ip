package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * The Duke class encapsulates the Duke project's chat-bot for CS2103T individual project 1.
 *
 * @author Chesterwongz
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    @SuppressWarnings("checkstyle:MemberName")
    private final String DEFAULT_FILEPATH = "tasks.txt";

    /**
     * Constructor for Duke when given a filepath.
     * @param filePath The path of the save file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Constructor for Duke when using default save file path.
     */
    public Duke() {
        storage = new Storage(DEFAULT_FILEPATH);
        tasks = new TaskList(storage.load());
    }

    /**
     * Get Duke's response to the user's command.
     *
     * @return Duke's response string to the given user command
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
