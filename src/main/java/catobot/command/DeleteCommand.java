package catobot.command;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

/**
 * Represents the command to delete a task.
 */
public class DeleteCommand extends Command {

    /** Content of the command. */
    private final int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param content The content of DeleteCommand.
     */
    protected DeleteCommand(String content) {
        String description = Parser.parseSingleArgument(content, CommandType.DELETE);
        this.index = Integer.parseInt(description);
    }

    /**
     * Deletes a task.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the command does not give a valid index number.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        try {
            return tasks.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

}
