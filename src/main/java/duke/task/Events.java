package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDate localDate;
    protected LocalTime localStartTime;
    protected LocalTime localEndTime;

    public Events(String description, LocalDate localDate, LocalTime localStartTime, LocalTime localEndTime) {
        super(description);
        this.localDate = localDate;
        this.localStartTime = localStartTime;
        this.localEndTime = localEndTime;
    }

    @Override
    public String toString() {
        return String.format("[E] [%s] " + this.description + " (at: %s %s-%s)",
            this.getStatusIcon(),
            this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
            this.localStartTime,
            this.localEndTime);
    }

    @Override
    public String toDataFileString() {
        return String.format("E|%s|%s|%s|%s|%s",
            this.isDone ? "1" : "0",
            this.description,
            this.localDate,
            this.localStartTime,
            this.localEndTime);
    }
}
