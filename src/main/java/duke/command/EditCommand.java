package duke.command;

import duke.Action;
import duke.Storage;
import duke.StringUtils;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class EditCommand extends Command {
    /** The index of the task. */
    private final int index;
    /** The new description */
    private final String description;

    /**
     * Constructs an edit command using the given index and new description.
     *
     * @param action The given action type.
     * @param index The given index.
     * @param description The given new description.
     */
    public EditCommand(Action action, int index, String description) {
        super(action, false);
        this.index = index;
        this.description = description;
    }


    /**
     * Executes the command and prints the result in console.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMultiLines(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task temp = taskList.getTask(index);
        temp.setDescription(description);
        taskList.setTask(temp, index);
        return StringUtils.getEditMessage(temp);
    }
}
