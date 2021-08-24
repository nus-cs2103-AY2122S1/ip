package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

/**
 * Represents the command to mark a task as done.
 */
public class DoneCommand extends Command {

    /** Content of the command. */
    private final String content;

    /**
     * Constructor for DoneCommand.
     *
     * @param content The content of the command.
     */
    protected DoneCommand(String content) {
        this.content = content;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks The list of tasks to be worked on.
     * @param ui The ui that responds to the user.
     * @param storage The storage of the tasks.
     * @throws BotException If the command does not give a valid index number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            int index = Integer.parseInt(content.substring("done".length()).trim());
            Ui.respond(tasks.completeTask(index));
        } catch(NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

}
