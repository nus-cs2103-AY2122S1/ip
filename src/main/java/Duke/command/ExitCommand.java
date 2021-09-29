package Duke.command;

import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return Ui.bye();
    }
}
