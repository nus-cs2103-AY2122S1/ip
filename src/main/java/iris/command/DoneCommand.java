package iris.command;

import iris.IrisException;
import iris.TaskList;
import iris.Ui;
import iris.task.Task;

public class DoneCommand extends ModifyTaskListCommand {
    private int index;
    private Task task;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void runSilently(TaskList tasks) throws IrisException {
        this.task = tasks.done(this.index);
    }

    @Override
    public void say(TaskList tasks, Ui ui) {
        ui.say(String.format("Good job! I've marked this task as done: %s", task));
    }
}
