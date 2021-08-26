package eightbit.command;

import eightbit.EightBitException;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    private final int index;

    /**
     * Constructs a command to mark a task as done.
     *
     * @param index Position of task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done.
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

        taskList.get(index).markAsDone();
        storage.rewriteFileWithTasks(taskList);
        ui.printWithLines("Great job on completing this task!\n" + taskList.get(index).toString());
    }
}
