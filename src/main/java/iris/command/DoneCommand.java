package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.task.Task;

public class DoneCommand extends ModifyTaskListCommand {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        Task task = tasks.done(this.index);
        return String.format("Good job! I've marked this task as done:\n%s", task);
    }
}
