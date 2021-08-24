package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

/**
 * Represents the command to delete a task.
 */
public class DeleteCommand extends Command {

    /** Content of the command. */
    private final String content;

    /**
     * Constructor for DeleteCommand.
     *
     * @param content The content of the command.
     */
    protected DeleteCommand(String content) {
        this.content = content;
    }

    /**
     * Deletes a task.
     *
     * @param tasks The list of tasks to be worked on.
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the command does not give a valid index number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            int index = Integer.parseInt(content.substring("delete".length()).trim());
            Ui.respond(tasks.deleteTask(index));
        } catch(NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

}
