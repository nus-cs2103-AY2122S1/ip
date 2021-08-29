package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the DoneCommand in the Duke program.
 */
public class DoneCommand extends Command {
    private final int doneTask;

    /**
     * Constructs a DoneCommand to mark a given task as done.
     *
     * @param doneTask Index of task to be marked as done.
     */
    public DoneCommand(int doneTask) {
        this.doneTask = doneTask;
    }

    /**
     * Defines the execution of the DoneCommand where the given task is marked as done.
     *
     * @param tasks   Tasks of the Duke program.
     * @param ui      Ui of the Duke program.
     * @param storage Storage of the Duke program.
     * @throws DukeException If the given task does not exist, or changes cannot be saved to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check for invalid task argument
        if (doneTask >= tasks.getSize()) {
            throw new InvalidArgumentException(tasks.getSize());
        }

        tasks.getTask(doneTask).markAsDone();

        String response = "Nice! I've marked this task as done:\n"
                + "       " + tasks.getTask(doneTask);
        ui.showResponse(response);

        storage.save(tasks.getTaskList());
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
