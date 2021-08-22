/** Command to add tasks */
public class AddTaskCommand extends Command {
    /** task to add */
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, UI ui, FileController fc) throws UnsavedChangesException {
        tasks.add(task);
        if (!fc.writeText(tasks.serialize())) {
            throw new UnsavedChangesException();
        }
        ui.printText(String.format("Added: %s\nNow you have %d tasks", task, tasks.size()));
    }
}
