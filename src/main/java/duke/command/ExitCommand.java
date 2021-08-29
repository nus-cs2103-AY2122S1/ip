package duke.command;

import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

/**
 * Represents a command for exiting the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String USAGE_MESSAGE = "To close Duke, use 'bye'.";

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) {
        storage.saveTasks(taskManager);
        return new DukeResponse("Goodbye. Hope to see you again soon!", true);
    }
}
