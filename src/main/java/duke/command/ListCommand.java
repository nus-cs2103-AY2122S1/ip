package duke.command;

import duke.main.TaskList;

public class ListCommand extends Command {

    private final TaskList taskList;
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        return taskList.toString();
    }
}
