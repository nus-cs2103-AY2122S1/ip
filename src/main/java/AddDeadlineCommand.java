import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private final Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.deadline);
        try {
            storage.updateFile(tasks.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
