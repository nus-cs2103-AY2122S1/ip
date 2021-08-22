package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Implements the logic for marking a Task as done in the users list.
 */
public class MarkDoneCommand extends Command{
    private final int index;

    /**
     * Constructor called when marking a Task as done
     * @param index Index indicates the Task to be marked.
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done and prints a message that reflects the change to notify the user.
     * @param tasklist TaskList that contains all the users current tasks.
     * @param ui Ui object for interaction with user
     * @param store Storage object that handles save and load functionality.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        tasklist.get(index).setFlag(true);
        ui.notifySuccessfulMarkDone(tasklist, index);
    }
}
