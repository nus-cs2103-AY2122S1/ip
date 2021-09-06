package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

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
     * @param taskList handles task operations including adding, deleting, marking as done and retrieval.
     * @return response message by chat bot when trying to execute specific command.
     * @throws DukeException thrown when a failure occurs within the Duke chat bot.
     */
    public abstract String execute(TaskList taskList) throws DukeException;

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
