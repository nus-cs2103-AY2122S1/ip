package duke;

/**
 * Command Interface
 */
public interface Command {

    /**
     * Gets the task done and returns the response that the chat box will show to the user
     *
     * @param tasks the TaskList
     * @param ui the Ui
     * @param storage the Storage
     * @return the corresponding response
     * @throws DukeException if user input is invalid
     */
    String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the Command is an Exit Command
     *
     * @return a boolean value, true if it is an Exit Command, false otherwise
     */
    boolean isExit();
}
