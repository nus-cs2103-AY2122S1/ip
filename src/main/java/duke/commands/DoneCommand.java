package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.gui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Encapsulates the information of a DoneCommand object that contains an id of the task.
 */
public class DoneCommand extends Command {
    private final int id;

    public DoneCommand(int id) {
        this.id = id;
    }

    @Override
    public String executeCommand(TaskList taskList) throws NoSuchTaskException {
        boolean isIdPositive = this.id >= 0;
        boolean isIdLessThanTaskCount = this.id < taskList.getTaskCount();
        boolean isIdWithinRange = isIdPositive && isIdLessThanTaskCount;

        if (isIdWithinRange) {
            Task task = taskList.getTask(this.id);
            taskList.markTaskAsComplete(this.id);
            return Ui.printTaskCompleteMessage(task);
        } else {
            throw new NoSuchTaskException();
        }
    }
}
