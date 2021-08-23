package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String deadLine;
    private LocalDateTime dateTime;

    public Deadline(String task, String deadLine) {
        super(task);
        this.deadLine = deadLine;
        timeSetter(deadLine);
        timeFormChange();
    }

    private void timeSetter(String timeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        dateTime = LocalDateTime.parse(timeInput, formatter);
    }

    private String timeFormChange() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h.mm a");
        return dateFormat.format(dateTime) + ", " + timeFormatter.format(dateTime);
    }

    @Override
    String printTask() {
        String result = "";
        if (super.complete) {
            result = "[D][X] ";
        } else {
            result = "[D][ ] ";
        }
        return result + super.task + "(by: " + timeFormChange() + ")";
    }
}
