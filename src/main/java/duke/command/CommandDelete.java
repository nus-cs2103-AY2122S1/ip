package duke.command;

import task.TaskList;

/**
 * Command to delete a task
 */
public class CommandDelete extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor
     *
     * @param taskList Task list
     * @param index Index to delete
     */
    public CommandDelete(TaskList taskList, int index) {
        this.commandName = "delete <index>";
        this.description = "Delete task from list";

        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Deletes the task at the index in the task list
     */
    @Override
    public void execute() {
        taskList.delete(index);
    }
}
