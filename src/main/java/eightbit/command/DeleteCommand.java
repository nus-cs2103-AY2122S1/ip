package eightbit.command;

import eightbit.EightBitException;
import eightbit.task.Task;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a command to delete a task.
     *
     * @param index Position of task in the list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete the task from the user's list.
     *
     * @param taskList User's list of tasks.
     * @param ui Ui responsible for printing messages.
     * @param storage Storage responsible for reading/writing the file.
     * @throws EightBitException If the given index exceeds the total number of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EightBitException {
        if (index >= taskList.size()) {
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }

        Task deletedTask = taskList.remove(index);
        storage.rewriteFileWithTasks(taskList);
        ui.printWithLines("Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
