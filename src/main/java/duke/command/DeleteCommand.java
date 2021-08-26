package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents the command when the user types "delete" validly.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand, which takes in the index of the task on the array list.
     *
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the necessary task on the task list, and saves.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.deleteTask(index);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
    }

    /**
     * Returns false as the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
