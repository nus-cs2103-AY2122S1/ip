package energy.result;

import energy.util.Parser;

/**
 * A class that represents the result of executing a command.
 */
public class Result {
    private final TaskList taskList;
    private final AliasHandler aliasHandler;
    private final String message;

    /**
     * Creates a Result object that contains task and alias data, as well as
     * an output message.
     *
     * @param taskList An object containing task data.
     * @param message  A string representing an output message.
     */
    public Result(TaskList taskList, String message) {
        this.taskList = taskList;
        this.aliasHandler = Parser.getAliasHandler();
        this.message = message;
    }

    /**
     * Creates a Result object that contains task and alias data, as well as
     * an output message.
     *
     * @param taskList     An object containing task data.
     * @param aliasHandler An object containing alias data.
     * @param message      A string representing an output message.
     */
    public Result(TaskList taskList, AliasHandler aliasHandler, String message) {
        this.taskList = taskList;
        this.aliasHandler = aliasHandler;
        this.message = message;
    }

    /**
     * Gets the alias data from the Result object.
     *
     * @return An AliasHandler object containing alias data.
     */
    public AliasHandler getAliasHandler() {
        return aliasHandler;
    }

    /**
     * Gets the output message from the Result object.
     *
     * @return A string representing an output message from executing a command.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the task data from the Result object.
     *
     * @return A TaskList object containing task data.
     */
    public TaskList getTaskList() {
        return taskList;
    }
}
