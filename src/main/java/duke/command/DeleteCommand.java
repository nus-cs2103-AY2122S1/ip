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
     * @param index index of the item to be deleted
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
     * @return output for this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String output = tasks.deleteTask(index);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        return output;
    }
}
