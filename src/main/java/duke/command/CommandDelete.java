package duke.command;

import task.TaskList;

/**
 * Command to delete a task.
 */
public class CommandDelete extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor for this command.
     *
     * @param taskList Task list to delete from.
     * @param index Index to delete.
     */
    public CommandDelete(TaskList taskList, int index) {
        this.commandName = "delete <index>";
        this.description = "Delete task from list";
        this.arguments = new String[]{
            "<index> Number, deletes task at index as displayed in list command"
        };

        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Deletes the task at the index in the task list.
     */
    @Override
    public String execute() {
        return taskList.delete(index);
    }
}
