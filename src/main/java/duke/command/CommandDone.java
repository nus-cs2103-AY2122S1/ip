package duke.command;

import task.TaskList;

/**
 * Command to toggle task completion
 */
public class CommandDone extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor
     *
     * @param taskList Task list
     * @param index Index to delete
     */
    public CommandDone(TaskList taskList, int index) {
        this.commandName = "done <index>";
        this.description = "Toggles completion of task";
        this.arguments = new String[]{
            "<index> Number, (un)completes task at index as displayed in list command"
        };

        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Toggles completion of task at index in task list
     */
    @Override
    public void execute() {
        taskList.toggleDone(index);
    }
}
