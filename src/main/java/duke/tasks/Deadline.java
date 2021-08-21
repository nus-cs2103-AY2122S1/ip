package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String marker = "D";
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public Deadline(String name, LocalDateTime deadline, boolean isDone) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTime() {
        return this.deadline.format(formatter);
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString()
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy EEE HH:mm")) + ")";
    }
}
