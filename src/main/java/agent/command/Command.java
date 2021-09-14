package agent.command;

import agent.exceptions.DukeException;
import agent.tasks.TaskList;

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
     * Returns true or false to indicate whether program should terminate after this command is executed.
     *
     * @return boolean value which indicates whether program should terminate after this command is executed.
     */
    public abstract boolean isExit();

    protected String getUserInputBody() {
        return this.userInputBody;
    }
}
