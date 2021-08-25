package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public String getWhen() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dtf.format(deadline);
    }

    @Override
    public String displayInfo() {
        return String.format("[D] [%s] %s (by: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }

    @Override
    public String getSaveInfo() {
        if (this.isDone()) {
            return String.format("D | 1 | %s | %s", this.getTaskName(), this.getWhen());
        } else {
            return String.format("D | 0 | %s | %s", this.getTaskName(), this.getWhen());
        }
    }
}
