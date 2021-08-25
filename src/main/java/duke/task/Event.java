package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    private LocalDateTime time;

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    public String getWhen() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dtf.format(time);
    }

    @Override
    public String displayInfo() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }

    @Override
    public String getSaveInfo() {
        if (this.isDone()) {
            return String.format("E | 1 | %s | %s", this.getTaskName(), this.getWhen());
        } else {
            return String.format("E | 0 | %s | %s", this.getTaskName(), this.getWhen());
        }
    }
}
