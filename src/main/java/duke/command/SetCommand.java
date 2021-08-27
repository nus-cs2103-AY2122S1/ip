package duke.command;

import duke.Action;
import duke.Storage;
import duke.task.TaskList;

public class SetCommand extends Command{
    /** The index of the task in the list. */
    private int index;

    /**
     * Constructs a set command using the given action and index.
     *
     * @param action The given action.
     * @param index The given index.
     */
    public SetCommand(Action action, int index) {
        super(action);
        this.index = index;
    }

    /**
     * Sets the task specified by the index to a new task.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.markTaskAsDone(index);
    }
}
