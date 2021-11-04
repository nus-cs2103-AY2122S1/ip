package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.task.Task;

public class DeleteCommand extends ModifyTaskListCommand {
    private int index;
    private Task task;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        this.task = tasks.delete(this.index);

        int count = tasks.getCount();
        return String.format(
                "Noted, I've removed this task:\n%s\nNow you have %d %s in the list.",
                this.task.toString(),
                count,
                count == 1 ? "task" : "tasks"
        );
    }
}
