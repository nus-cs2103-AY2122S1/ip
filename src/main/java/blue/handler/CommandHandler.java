package blue.handler;

import blue.BlueException;
import blue.TaskList;

public abstract class CommandHandler {
    protected TaskList taskList;

    protected CommandHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * @param input - the input entered by the user
     * @return response
     */
    public abstract String handle(String input) throws BlueException;
}
