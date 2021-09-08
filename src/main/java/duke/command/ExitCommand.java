package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * ExitCommand class handles the 'bye' command to close Duke.
 */
public class ExitCommand extends Command {

    static final String BYE_MESSAGE = "Goodbye! Hope to see you again soon!\n";

    /**
     * Returns the exit message.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return BYE_MESSAGE;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
