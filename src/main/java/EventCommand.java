import java.time.LocalDateTime;

public class EventCommand extends Command{
    private String task;
    private LocalDateTime time;

    public EventCommand(String task, LocalDateTime time) {
        this.task = task;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        Event event = new Event(task, time);
        tasks.add(event, true);
        String saveFileString = tasks.save();
        storage.save(saveFileString);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
