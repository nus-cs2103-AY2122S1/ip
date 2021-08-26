package duke;

public class AddCommand implements Command {
    private Task pendingTask;

    public AddCommand(Task newTask) {
        this.pendingTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(pendingTask);
        ui.announceNewTask(pendingTask);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
