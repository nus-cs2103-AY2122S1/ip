package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/** Encapsulates the logic unit of the application. */
public class Duke {
    /** Hardcoded file path to save location. */
    private static final String FILEPATH = "./data/tasks.json";

    /** The storage utility. */
    private Storage storage;

    /** The list of tasks. */
    private TaskList tasks;

    private boolean isExit;

    /** Default Duke constructor. */
    public Duke() {
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Feeds user's command to the program logic and returns a response as a string.
     *
     * @param input The user's input.
     * @return The response from executing the command.
     */
    public String process(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Checks if the last user command is an exit command.
     *
     * @return True if the last user command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
