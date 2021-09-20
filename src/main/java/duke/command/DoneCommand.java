package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command{
    private final TaskList taskList;
    private final Ui textUi;
    private final int taskIndex;


    public DoneCommand(TaskList taskList, Ui ui, int index) {
        assert index > 0: "Invalid index";
        this.taskList = taskList;
        this.textUi = ui;
        this.taskIndex = index;
    }

    @Override
    public String execute() {
        Task task = taskList.get(taskIndex - 1);
        taskList.markDone(taskIndex - 1);
        return textUi.done(task);
    }
}
