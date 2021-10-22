package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;




/**
 * Encapsulates the Duke bot that has the ability to create, read, update and delete tasks
 * such as todo, deadline and event tasks based on user input.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     *
     * @param filePath user filepath to store text file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
        } catch (DukeException | IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Parses input command and save text file.
     *
     * @param command user input command
     * @return message for user
     */
    public String getResponse(String command) {
        String message = "";
        try {
            message = message.concat(Parser.parse(command).execute(taskList));
            storage.saveFile(taskList);
            return message;
        } catch (DukeException | IOException e) {
            message = message.concat(e.getMessage());
            return message;
        }
    }
}
