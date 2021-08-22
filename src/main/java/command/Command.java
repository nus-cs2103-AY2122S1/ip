package command;

import exceptions.DukeException;
import io.UserOutputHandler;
import tasks.TaskList;

import java.io.IOException;

public abstract class Command {
    private final String userInputBody;

    protected Command(String userInputBody) {
        this.userInputBody = userInputBody;
    }

    /**
     * Handles the command.
     *
     * @param userOutputHandler handles outputting messages to the output destination
     * @param taskList          handles task operations including adding, deleting, marking as done and retrieval
     * @throws IOException   thrown when failure reading/writing to the target storage occurs.
     * @throws DukeException thrown when user input is invalid
     */
    public abstract void execute(UserOutputHandler userOutputHandler, TaskList taskList)
            throws IOException, DukeException;

    public abstract boolean isExit();

    protected String getUserInputBody() {
        return this.userInputBody;
    }
}
