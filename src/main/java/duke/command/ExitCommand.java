package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * ExitCommand class handles the 'bye' command to close Duke.
 */
public class ExitCommand extends Command {

    /**
     * Returns the exit message.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If error occur during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Goodbye! Hope to see you again soon!\n";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
