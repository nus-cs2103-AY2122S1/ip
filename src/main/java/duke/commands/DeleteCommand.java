package duke.commands;

import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void executeCommand(TaskList taskList) throws NoSuchTaskException {
        if (this.id >= 0 && this.id < taskList.getTaskCount()) {
            Task task = taskList.getTask(this.id);
            taskList.deleteTask(this.id);
            Ui.printDeleteTaskMessage(task, taskList.getTaskCount());
        } else {
            throw new NoSuchTaskException();
        }
    }
}
