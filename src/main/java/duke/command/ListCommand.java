package duke.command;

import duke.main.TaskList;

public class ListCommand extends Command {

    private TaskList taskList;
    public ListCommand(TaskList taskList) {
        super("list", taskList);
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        return taskList.toString();
    }
}
