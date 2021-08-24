package iris.command;

import iris.TaskList;
import iris.Ui;

public class ListCommand extends Command {
    @Override
    public void runSilently(TaskList tasks) {

    }

    @Override
    public void say(TaskList tasks, Ui ui) {
        for (int i = 0; i < tasks.getCount(); i++) {
            ui.say(String.format("%d. %s", i + 1, tasks.get(i)), i == 0);
        }
    }
}
