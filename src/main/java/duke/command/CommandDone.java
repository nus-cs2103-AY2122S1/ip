package duke.command;

import task.TaskList;

public class CommandDone extends Command {
    private final TaskList taskList;
    private final int index;

    public CommandDone(TaskList taskList, int index) {
        this.commandName = "done <index>";
        this.description = "Toggles completion of task";

        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public void execute() {
        taskList.toggleDone(index);
    }
}
