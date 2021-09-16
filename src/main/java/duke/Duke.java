package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExitException;
import duke.storage.Storage;

/**
 * Main class for Duke.
 */
public class Duke {
    private Parser parser;
    private TaskArrayList taskList;
    private Path storagePath;

    /**
     * Create a Duke instance
     */
    public Duke() {
        storagePath = Paths.get(".", "data", "duke.txt");
        try {
            this.taskList = Storage.load(storagePath);
        } catch (DukeException | IOException e) {
            this.taskList = new TaskArrayList();
        }
        this.parser = new Parser(taskList);
    }

    /**
     * Get the response for a given user command input
     *
     * @param userInput command typed by user
     * @return Message to print on screen for user
     * @throws DukeExitException when response is an exit signal, caught by caller to prepare teardown
     */
    public String getResponse(String userInput) throws DukeException {
        try {
            return parser.run(userInput);
        } catch (DukeExitException e) {
            throw e;
        } catch (DukeException e) {
            throw e;
        } finally {
            Storage.dump(taskList, storagePath);
        }
    }

}
