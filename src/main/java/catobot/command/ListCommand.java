package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;

/**
 * Represents the command to list the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for a ListCommand.
     */
    protected ListCommand() {}

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be worked on.
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the command is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        Ui.respond(tasks.display());
    }
}
