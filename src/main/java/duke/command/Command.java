package duke.command;

import duke.exceptions.DukeException;
import duke.io.UserOutputHandler;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents a user command.
 *
 * @author kevin9foong
 */
public abstract class Command {
    private final String userInputBody;

    protected Command(String userInputBody) {
        this.userInputBody = userInputBody;
    }

    /**
     * Handles this command.
     *
     * @param userOutputHandler handles outputting messages to the output destination.
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval.
     * @throws IOException   thrown when failure reading/writing to the target storage occurs.
     * @throws DukeException thrown when user input is invalid.
     */
    public abstract void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, DukeException;

    /**
     * Checks if program should terminate after this command.
     *
     * @return boolean value which indicates whether program should terminate after command.
     */
    public abstract boolean isExit();

    protected String getUserInputBody() {
        return this.userInputBody;
    }
}
