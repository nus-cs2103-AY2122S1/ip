package duke.commands;

import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a DeleteCommand object that contains an id of the task.
 */
public class DeleteCommand extends Command {
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public String executeCommand(TaskList taskList) throws NoSuchTaskException {
        if (this.id >= 0 && this.id < taskList.getTaskCount()) {
            Task task = taskList.getTask(this.id);
            taskList.deleteTask(this.id);
            return Ui.printDeleteTaskMessage(task, taskList.getTaskCount());
        } else {
            throw new NoSuchTaskException();
        }
    }
}
