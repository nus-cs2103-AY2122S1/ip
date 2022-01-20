package duke;

/**
 * Represents a state of history where a command has led to the task list.
 */
public class HistoryState {
    private TaskList taskList;
    private Command command;

    /**
     * Creates a history tagging a command to the task list right after executing the command.
     * @param taskList
     * @param command
     */
    public HistoryState(TaskList taskList, Command command) {
        this.taskList = taskList.clone();
        this.command = command.clone();
    }

    /**
     * Returns the task list of the history,
     * @return The task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the command of the history;
     * @return The command.
     */
    public Command getCommand() {
        return command;
    }
}
