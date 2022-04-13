package blue.handler;

import blue.BlueException;
import blue.TaskList;

/**
 * Holds the similarity of all command handlers.
 */
public abstract class CommandHandler {
    protected TaskList taskList;

    protected CommandHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Handles the user input.
     *
     * @param input User input.
     * @return Response.
     */
    public abstract String handle(String input) throws BlueException;
}
