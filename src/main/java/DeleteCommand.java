import java.util.List;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> savedTasks = tasks.getTasks();
        if (this.index > savedTasks.size() || this.index < 1) {
            DukeException exception = new DukeException("Number out of range!");
            System.out.println(exception);
        }
        else {
            Task removedTask = savedTasks.get(this.index-1);
            tasks.deleteTask(this.index-1);
            System.out.println("Alright! I've removed this task:\n" + removedTask);
            System.out.println(String.format("Now you have %d tasks left in the list!", savedTasks.size()));
        }
    }
}
