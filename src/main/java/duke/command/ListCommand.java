package duke.command;

import duke.util.Reply;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class encapsulating the logic that occurs when the user issues a 'list' command.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Causes the Ui to print the task list
     *
     * @param tasks List of existing tasks
     * @param storage Storage class handling the persistence of the tasks
     * @return CommandResult of the encapsulating the effects of the command after it completes
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        assert storage != null;
        return new CommandResult(Reply.showList(tasks), true, super.isExit());
    }

    /**
     * Indicate if another object is 'equal' to this object.
     *
     * @param obj Reference object with which to compare to.
     * @return true if they are equal.
     *         false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
