package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final String taskInfo;

    /**
     * Constructor of TodoCommand class. Initialize a TodoCommand instance
     * from a given TaskList, Ui, taskInfo.
     *
     * @param taskList A list of tasks
     * @param ui A user interface
     * @param taskInfo The description of task
     */
    public TodoCommand(TaskList taskList, Ui ui, String taskInfo) {
        this.taskList = taskList;
        this.textUi = ui;
        this.taskInfo = taskInfo;
    }

    @Override
    public String execute() {
        Task todo = new Todo(taskInfo);
        taskList.add(todo);
        return textUi.add(taskList, todo);
    }

}
