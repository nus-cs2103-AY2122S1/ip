package duke.command;

import duke.Duke;
import duke.Storage;
import duke.task.TaskList;

/**
 * ExitCommand class encapsulates command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage) {
        String message = "\"Bye. Hope to see you again soon!\"";
        duke.setResponse(message);
    }
}
