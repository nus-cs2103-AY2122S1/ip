package cs2103t.duke.command;

import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.dismiss();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

}
