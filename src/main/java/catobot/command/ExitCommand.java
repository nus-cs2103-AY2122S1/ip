package catobot.command;

import java.io.IOException;

import catobot.Catobot;
import catobot.Storage;
import catobot.exception.BotException;
import catobot.item.TaskList;

/**
 * Represents the command to exit from Ui.
 */
public class ExitCommand extends Command {
    private static final String ERROR = "Meow! There is an error occured when trying to save the tasks >.<";

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the command is not valid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        try {
            storage.write(tasks.toStringInDoc());
            return Catobot.BYE;
        } catch (IOException e) {
            return ERROR;
        }
    }
}
