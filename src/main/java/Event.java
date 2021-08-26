import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    @Override
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "E | " + mark + " | " + this.description +" | " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public void excute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
        storage.writeData(task.getTasks());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
