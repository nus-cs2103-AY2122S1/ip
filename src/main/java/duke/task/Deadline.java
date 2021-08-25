package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(boolean done, String name, LocalDateTime dateTime) {
        super(done, name);
        this.dateTime = dateTime;
    }

    @Override
    public String encode() {
        return String.format("D|%s|%s", super.encode(), dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime.format(dateTimeFormatter));
    }
}
