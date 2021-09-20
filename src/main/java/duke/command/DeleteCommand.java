package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final int taskIndex;

    /**
     * Constructor of DeleteCommand class. Initialize a DeleteCommand instance
     * from a given TaskList, Ui, taskIndex.
     *
     * @param taskList A list of tasks
     * @param ui A user interface
     * @param index Index of the deleted task in taskList
     */
    public DeleteCommand(TaskList taskList, Ui ui, int index) {
        this.taskList = taskList;
        this.textUi = ui;
        this.taskIndex = index;
    }

    @Override
    public String execute() {
        Task task = taskList.get(taskIndex - 1);
        taskList.delete(taskIndex - 1);
        return textUi.delete(taskList, task);
    }
}
