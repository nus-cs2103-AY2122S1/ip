package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.Deadlines;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Adds deadline task into task list and storage file.
 */
public class AddDeadlineCommand extends Command {
    protected String description;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param description Name and date of deadline task.
     */
    public AddDeadlineCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Executes the adding of deadline task in the task list and storage file.
     *
     * @param tasks Existing tasks in the task list.
     * @param ui User input format.
     * @param storage Stores created command into the txt file
     * @return String message for Tokio's reply.
     * @throws IOException If task cannot be written to the storage file.
     * @throws DukeException If date or command is in the wrong format or if task already exists.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        String[] descDateArray = description.split("/by ");
        if (descDateArray.length < 2) {
            throw new DukeException("Something is missing from this deadline!\n" + "Rio, please follow this format:\n"
                + "deadline {name} /by {yyyy-MM-dd}");
        }
        String deadlineDesc = descDateArray[0].trim();
        String deadlineDate = descDateArray[1].trim();
        Deadlines addDeadline = new Deadlines(deadlineDesc, deadlineDate);
        if (!tasks.addTask(addDeadline)) {
            throw new DukeException("Oops Rio, this deadline task already exists!\n");
        }
        storage.writeTask(addDeadline);
        return ui.printAddCommand(addDeadline, tasks);
    }

    /**
     * Indicates that add deadline command is non-terminating.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
