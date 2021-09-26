package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the DeleteCommand in the Duke program.
 */
public class DeleteCommand extends Command {
    private final int deleteTask;

    /**
     * Constructs a DeleteCommand to delete a given task in tasks.
     *
     * @param deleteTask Index of task to be deleted.
     */
    public DeleteCommand(int deleteTask) {
        this.deleteTask = deleteTask;
    }

    /**
     * Returns the response after deleting the given task.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     * @throws DukeException If the given task does not exist, or changes cannot be saved to storage.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check for invalid task argument
        if (deleteTask >= tasks.getSize()) {
            throw new InvalidArgumentException(tasks.getSize());
        }

        String response = "Noted. I've removed this task:\n"
                + " " + tasks.getTask(deleteTask) + "\n"
                + "Now you have "
                + (tasks.getSize() - 1) + (tasks.getSize() - 1 > 1 ? " tasks" : " task")
                + " in the list.";

        tasks.deleteTask(deleteTask);

        storage.save(tasks.getTaskList());

        return response;
    }

    /**
     * Returns false as this command is not the ExitCommand.
     *
     * @return false as this command is not the ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
