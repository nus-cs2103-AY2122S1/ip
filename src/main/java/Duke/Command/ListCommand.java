package Duke.Command;

import Duke.Main.TaskList;
import Duke.Task.Task;

import java.util.List;

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
