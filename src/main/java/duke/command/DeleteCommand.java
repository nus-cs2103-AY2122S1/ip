package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;


public class DeleteCommand extends Command {
    private boolean isExit = false;
    private int taskid;
    private Task removedTask;


    public DeleteCommand(int taskid) {
        this.taskid = taskid;
    }

    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) throws DukeException {
        removedTask = tasks.delete(this.taskid);
        userInt.notifyDelete(removedTask);
        storage.save(tasks);
    }

    @Override
    public Task getTask() {
        return this.removedTask;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
