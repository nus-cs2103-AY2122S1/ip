package duke.command;

import task.TaskList;

/**
 * Command to toggle task completion.
 */
public class CommandDone extends Command {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor for this command.
     *
     * @param taskList Task list to toggle completion from.
     * @param index Index to toggle completion.
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
     * Toggles completion of task at index in task list.
     */
    @Override
    public String execute() {
        return taskList.toggleDone(index);
    }
}
