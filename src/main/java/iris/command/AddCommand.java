package iris.command;

import iris.TaskList;
import iris.Ui;

public abstract class AddCommand extends ModifyTaskListCommand {
    @Override
    public void say(TaskList tasks, Ui ui) {
        int count = tasks.getCount();
        ui.say("Got it. I've added this task:");
        ui.say(tasks.get(count - 1).toString(), false);
        ui.say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }
}
