package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private final TaskList taskList;
    private final Ui textUi;
    private final String taskInfo;

    public TodoCommand(TaskList taskList, Ui ui,String taskInfo) {
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
