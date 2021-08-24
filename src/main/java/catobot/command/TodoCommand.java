package catobot.command;

import catobot.Storage;
import catobot.Ui;
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
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the description is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String description = content.substring("todo".length()).trim();
        Ui.respond(tasks.add(Todo.of(description)));
    }

}
