package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a Command that deletes a task from the TaskList.
 * A DeleteCommand contains an index representing the index of the task in the TaskList to be deleted.
 */

public class DeleteCommand extends Command {

    private int index;

    /**
     * Creates a DeleteCommand Object.
     *
     * @param index Index of task to be deleted in the TaskList
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from TaskList.
     *
     * @param tasks TaskList to delete task from.
     * @param ui UI that reflects the changes made to the TaskList.
     * @param storage unused.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.printDelete(tasks, index);
        tasks.delete(index);
    }
    
}