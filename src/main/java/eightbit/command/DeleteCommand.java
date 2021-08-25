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

    public DeleteCommand(int index) {
        this.index = index;
    }

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
