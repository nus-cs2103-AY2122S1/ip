import java.io.FileNotFoundException;

public class EventCommand extends Command {

    private String description;
    private String time;

    public EventCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.addEvent(description, time, storage, ui);
    }
}
