package duke.command;

import duke.Action;
import duke.Storage;
import duke.StringUtils;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    /** The index of the task in the list. */
    private final int index;

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
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMultiLines(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the delete command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert super.getAction() == Action.DELETE : "Delete command action type error";
        Task temp = taskList.removeTask(index);
        return StringUtils.getRemoveTaskMessage(temp, taskList.getSize());
    }
}
