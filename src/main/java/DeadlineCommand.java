import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String task;
    private LocalDateTime time;

    public DeadlineCommand(String task, LocalDateTime time) {
        this.task = task;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        Deadline deadline = new Deadline(task, time);
        tasks.add(deadline, true);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
