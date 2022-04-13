package duke.command;

import java.io.IOException;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the delete command.
 */
public class DeleteCommand extends Command {

    /** Represents the delete command keyword. */
    public static final String COMMAND = "delete";

    /** Index of task to delete. */
    private final int index;

    /**
     * Constructor of DeleteCommand.
     *
     * @param index index of task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return appropriate message when a task is deleted.
     * @throws EmptyListException if task list is already empty.
     * @throws InvalidIndexException if index is out of range of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyListException,
            InvalidIndexException, IOException {
        String message = tasks.deleteTask(index);
        storage.save(tasks);
        return message;
    }
}
