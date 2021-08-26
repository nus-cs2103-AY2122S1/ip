package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String USAGE_MESSAGE = "To find a task with a matching name, use 'find <search-string>'.";

    private final String commandArguments;

    public FindCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new DukeException("Invalid use of the 'find' command.\n\n" + USAGE_MESSAGE);
        }
        ui.print(taskManager.list(commandArguments));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
