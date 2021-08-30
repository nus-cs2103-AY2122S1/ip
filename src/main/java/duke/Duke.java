package duke;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;

import java.io.IOException;


/**
 * Encapsulates the Duke bot that has the ability to create, read, update and delete tasks
 * such as todo, deadline and event tasks based on user input.
 */
public class Duke  {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath user filepath to store text file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException | IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parse user input command to return message for user and save text file.
     *
     * @param command user input command
     * @return message for user
     */
    public String getResponse(String command) {
        String message = "Duke:\n";
        try {
            message = message.concat(Parser.parse(command).execute(tasks));
            storage.saveFile(tasks);
            return message;
        } catch (DukeException | IOException e) {
            message = message.concat(e.getMessage());
            return message;
        }
    }
}
