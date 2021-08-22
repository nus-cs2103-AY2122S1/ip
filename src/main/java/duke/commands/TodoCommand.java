package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    private final Todo task;

    public TodoCommand(Todo task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskList taskList) {
        taskList.addTask(this.task);
        Ui.printAddTaskMessage(this.task, taskList.getTaskCount());
    }
}
