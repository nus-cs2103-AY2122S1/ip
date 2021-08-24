package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.Ui;
import iris.task.Task;

public class DeleteCommand extends ModifyTaskListCommand {
    private int index;
    private Task task;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {
        this.task = tasks.delete(this.index);
    }

    @Override
    public void say(TaskList tasks, Ui ui) {
        ui.say("Noted. I've removed this task:");
        ui.say(this.task.toString(), false);
        int count = tasks.getCount();
        ui.say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }
}
