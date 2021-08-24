package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task{

    private String deadLine;
    private LocalDateTime dateTime;

    public DeadLine(String task, String deadLine) {
        super(task);
        this.deadLine = deadLine;
        timeSetter(deadLine);
        timerChange();
    }

    private void timeSetter(String timeInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        dateTime = LocalDateTime.parse(timeInput, formatter);
    }

    public String timerChange() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h.mm a");
        return dateFormat.format(dateTime) + ", " + timeFormatter.format(dateTime);
    }

    @Override
    public String printTask() {
        String result = "";
        if (super.complete) {
            result = "[D][X] ";
        } else {
            result = "[D][ ] ";
        }
        return result + super.task + " (by: " + timerChange() + ")";
    }
}
