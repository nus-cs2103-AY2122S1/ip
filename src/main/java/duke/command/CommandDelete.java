package duke.command;

import task.TaskList;

public class CommandDelete extends Command {
    private final TaskList taskList;
    private final int index;

    public CommandDelete(TaskList taskList, int index) {
        this.commandName = "delete <index>";
        this.description = "Delete task from list";

        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        taskList.delete(index);
    }
}
