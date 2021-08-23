package duke.command;

import duke.TaskManager;
import duke.Ui;

/**
 * Represents the command "exit", returns True when checked for isExit().
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.reply("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
