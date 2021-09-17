package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that exits the bot.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return ui.bye();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }
}
