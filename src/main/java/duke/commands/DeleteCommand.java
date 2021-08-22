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
        if (id >= 0 && id < taskList.getTaskCount()) {
            Task task = taskList.getTask(id);
            taskList.deleteTask(id);
            Ui.printDeleteTaskMessage(task, taskList.getTaskCount());
        } else {
            throw new NoSuchTaskException();
        }
    }
}
