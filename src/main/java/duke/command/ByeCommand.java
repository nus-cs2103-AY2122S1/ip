package duke.command;

import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;


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
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        assert storage != null;
        return new CommandResult(Reply.showBye(), true, super.isExit());
    }
}
