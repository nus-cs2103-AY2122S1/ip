package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private boolean isExit = false;
    private int taskid;
    private Task doneTask;

    public DoneCommand(int taskid) {
        this.taskid = taskid;
    }

    @Override
    public void execute(TaskList tasks, UI userInt, Storage storage) throws DukeException {
        doneTask = tasks.markDone(this.taskid);
        userInt.notifyDone(doneTask);
        storage.save(tasks);
    }

    @Override
    public Task getTask() {
        return this.doneTask;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
