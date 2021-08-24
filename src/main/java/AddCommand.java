import java.util.ArrayList;

public class AddCommand extends Command{
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

    public boolean isExit() {
        return this.isExit;
    }
}
