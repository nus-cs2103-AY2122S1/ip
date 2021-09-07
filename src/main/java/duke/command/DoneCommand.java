package duke.command;

import java.io.IOException;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the done command.
 */
public class DoneCommand extends Command {

    /** Represents the done command keyword. */
    public static final String COMMAND = "done";

    /** Index of task to be marked as done. */
    private final int task;

    /**
     * Constructor of DoneCommand.
     *
     * @param task
     */
    public DoneCommand(int task) {
        this.task = task;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return appropriate message for marking a task as done.
     * @throws InvalidIndexException if the index of the task is out of bounds of the task list.
     * @throws EmptyListException if the task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException,
            EmptyListException, IOException {
        String message = tasks.markDone(task);
        storage.save(tasks);
        return message;
    }
}
