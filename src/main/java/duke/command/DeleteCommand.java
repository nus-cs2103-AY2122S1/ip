package duke.command;

import duke.Action;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command{
    /** The index of the task in the list. */
    private int index;

    /**
     * Constructs a delete command using the given action and index.
     *
     * @param action The given action.
     * @param index The given index.
     */
    public DeleteCommand(Action action, int index) {
        super(action);
        this.index = index;
    }

    /**
     * Deletes the task specified by the index in the list.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Task temp = taskList.removeTask(index);
        Ui.showRemoveTaskMessage(temp, taskList.getSize());
    }
}
