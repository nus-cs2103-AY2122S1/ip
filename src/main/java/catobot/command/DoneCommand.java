package catobot.command;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

/**
 * Represents the command to mark a task as done.
 */
public class DoneCommand extends Command {

    /** Content of the command. */
    private final int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param content The content of the DoneCommand.
     */
    protected DoneCommand(String content) {
        String description = Parser.parseSingleArgument(content, CommandType.DONE);
        this.index = Integer.parseInt(description);
    }

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the command does not give a valid index number.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        try {
            return tasks.completeTask(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

}
