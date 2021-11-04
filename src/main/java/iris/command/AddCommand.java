package iris.command;

import iris.IrisException;
import iris.TaskList;

public abstract class AddCommand extends ModifyTaskListCommand {
    @Override
    public String run(TaskList tasks) throws IrisException {
        assert tasks != null;
        int count = tasks.getCount();

        return String.format(
                "Noted, I've added this task:\n%s\nNow you have %d %s in the list.",
                tasks.get(count - 1).toString(),
                count,
                count == 1 ? "task" : "tasks"
        );
    }
}
