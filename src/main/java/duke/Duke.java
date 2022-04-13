package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import javafx.application.Platform;

/**
 * Represents Duke, a program that helps people to keep track of tasks.
 *
 * @author ruiquan
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The path to a text file for storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the appropriate response given an input by the user.
     *
     * @param input The input by the user.
     * @return The response by Duke.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = parser.parse(input);
            response = c.execute(tasks, storage);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
