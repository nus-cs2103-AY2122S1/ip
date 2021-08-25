package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String USAGE_MESSAGE = "To delete a task, use 'delete <task-number>'.";

    private final String commandArguments;

    public DeleteTaskCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            throw new DukeException("Invalid use of the 'delete' command.\n\n" + USAGE_MESSAGE);
        }
        try {
            int taskNumber = Integer.parseInt(commandArguments);
            ui.print(taskManager.deleteTask(taskNumber));
            storage.saveTasks(taskManager.toText());
        } catch (NumberFormatException e) {
            // User provided an argument that is not parsable.
            throw new DukeException("Invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
