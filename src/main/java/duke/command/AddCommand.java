package duke.command;


import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Task;
import java.util.ArrayList;

public class AddCommand extends Command {
    private boolean isExit = false;
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList task, UI userInt, Storage storage) throws DukeException {
        task.add(this.newTask);
        ArrayList<Task> taskArrList = task.getAllTasks();
        userInt.notifyAdd(taskArrList);
        storage.save(task);
    }

    @Override
    public Task getTask() {
        return this.newTask;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
