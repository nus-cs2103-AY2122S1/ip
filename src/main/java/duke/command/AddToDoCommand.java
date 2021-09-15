package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidCommandUsageException;
import duke.response.DukeResponse;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.task.ToDo;

/**
 * Represents a command for adding a new <code>ToDo</code>.
 */
public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String USAGE_MESSAGE = "To add a new todo, use 'todo <name>'.";

    private final String commandArguments;

    public AddToDoCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public DukeResponse execute(TaskManager taskManager, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new InvalidCommandUsageException(COMMAND_WORD, USAGE_MESSAGE);
        }
        ToDo toDo = new ToDo(commandArguments);
        String message = taskManager.addTask(toDo);
        storage.saveTasks(taskManager);
        return new DukeResponse(message);
    }
}
