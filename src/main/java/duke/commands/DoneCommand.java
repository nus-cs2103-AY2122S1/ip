package duke.commands;

import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    private final int id;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public void executeCommand(TaskList taskList) throws NoSuchTaskException {
        if (id >= 0 && id < taskList.getTaskCount()) {
            Task task = taskList.getTask(id);
            task.markAsComplete();
            Ui.printCompleteTaskMessage(task);
        } else {
            throw new NoSuchTaskException();
        }
    }
}
