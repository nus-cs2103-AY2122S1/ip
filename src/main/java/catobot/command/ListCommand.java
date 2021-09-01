package catobot.command;

import catobot.Storage;
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
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the command is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        return tasks.display();
    }
}
