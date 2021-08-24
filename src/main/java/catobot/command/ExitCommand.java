package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;

import java.io.IOException;

/**
 * Represents the command to exit from Ui.
 */
public class ExitCommand extends Command {

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks to be worked on.
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the command is not valid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            storage.write(tasks.toStringInDoc());
            ui.exit();
        } catch (IOException e) {
            ui.showError("Meow! There is an error occured when trying to save the tasks >.<");
        }
    }

    /**
     * Checks if the command is to exit.
     *
     * @return True if the command is to exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
