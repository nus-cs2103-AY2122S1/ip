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
    private final String content;

    /**
     * Constructor for TodoCommand.
     *
     * @param content The content of the command.
     */
    protected TodoCommand(String content) {
        this.content = content;
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
        int startIndex = CommandType.TODO.getValue().length();
        String description = content.substring(startIndex).trim();
        return tasks.add(Todo.of(description));
    }

}
