

public class DoneCommand extends Command {
    public DoneCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskNo = Integer.valueOf(args[0]);
        Pair<Boolean, Task> statusTaskPair = tasks.markTaskDone(taskNo);
        boolean isTaskAlreadyDone = statusTaskPair.getFirst();
        Task task = statusTaskPair.getSecond();

        if (isTaskAlreadyDone) {
            ui.showError(String.format("Task %s is already done!\n  %s", taskNo, task));
        } else {
            ui.showDoneTask(task);
        }
        storage.writeToFile(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
