package duke.commands;

import duke.gui.Ui;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Encapsulates the information of a TodoCommand object that contains a Todo.
 */
public class TodoCommand extends Command {
    private final Todo task;

    public TodoCommand(Todo task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        taskList.addTask(this.task);

        return Ui.printAddTaskMessage(this.task, taskList.getTaskCount());
    }
}
