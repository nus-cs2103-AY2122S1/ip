package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command that marks a task as completed in a TaskList
 * A DoneCommand contains an index representing the index of the task in the TaskList that was completed.
 */
public class DoneCommand extends Command {

    private int toComplete;

    /**
     * Creates a DoneCommand Object.
     *
     * @param toComplete Index of a task to be completed in the TaskList.
     */
    public DoneCommand(int toComplete) {
        this.toComplete = toComplete;
    }

    /**
     * Marks the task as completed.
     *
     * @param tasks TaskList that contains the task to be marked as done.
     * @param ui UI that reflects the changes made to the TaskList.
     * @param storage unused.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDone(tasks, toComplete);
        tasks.get(toComplete).complete();
    }

}