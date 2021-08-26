import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        TaskList newTasks = tasks.deleteTask(input, ui);
        storage.saveTasksToFile(newTasks);
        return newTasks;
    }
}
