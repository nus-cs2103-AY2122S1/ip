package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;

public class LoadCommand extends Command {
    public static final String COMMAND_WORD = "load";
    public static final String USAGE_MESSAGE = "To load tasks from a file, use 'load <file-path>'.";

    private final String commandArguments;

    public LoadCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new DukeException("Invalid use of the 'load' command.\n\n" + USAGE_MESSAGE);
        }
        storage.setFilePath(commandArguments);
        taskManager.setTaskList(storage.loadTasks());
        return new DukeResponse("File successfully loaded! " + storage.getTasksLoadedMessage(taskManager));
    }
}
