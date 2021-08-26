package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskManager;
import duke.ui.Ui;
import duke.util.DukeDateTime;

/**
 * Represents a command for adding a new <code>Deadline</code>.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String USAGE_MESSAGE = "To add a new deadline, use 'deadline <name> /by <due-date>'.";

    private final String commandArguments;

    public AddDeadlineCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        String[] deadlineDetails = commandArguments.split("\\s+/by\\s+", 2);
        if (deadlineDetails.length < 2) {
            throw new DukeException("Invalid use of the 'deadline' command.\n\n" + USAGE_MESSAGE);
        }
        String deadlineName = deadlineDetails[0];
        DukeDateTime deadlineDueDate = DukeDateTime.parseUserInputDateTime(deadlineDetails[1]);
        Deadline deadline = new Deadline(deadlineName, deadlineDueDate);
        ui.print(taskManager.addTask(deadline));
        storage.saveTasks(taskManager);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
