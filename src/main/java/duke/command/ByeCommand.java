package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Reply;


/**
 * A command class encapsulating the logic that occurs when the user issues a 'bye' command.
 */
public class ByeCommand extends Command {
    /**
     * Constructor of the ByeCommand class
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Causes the UI to print a bye message.
     *
     * @param tasks List of existing tasks
     * @param reply User interface current interacting with the user
     * @param storage Storage class handling the persistence of the tasks
     */
    @Override
    public CommandResult execute(TaskList tasks, Reply reply, Storage storage) {
        return new CommandResult(Reply.showBye(), true, super.isExit());
    }
}
