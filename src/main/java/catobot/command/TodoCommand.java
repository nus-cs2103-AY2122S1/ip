package catobot.command;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.item.TaskList;
import catobot.item.Todo;

/**
 * Represents the command to add a todo to tasks.
 */
public class TodoCommand extends Command {

    /** Content of the command. */
    private final String description;

    /**
     * Constructor for TodoCommand.
     *
     * @param content The content of the command.
     */
    protected TodoCommand(String content) {
        this.description = Parser.parseSingleArgument(content, CommandType.TODO);
    }

    /**
     * Adds a todo to a list of tasks.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The text to display.
     * @throws BotException If the description is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        return tasks.add(Todo.of(description));
    }

}
