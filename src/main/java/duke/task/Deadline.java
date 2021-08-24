package duke.task;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {


    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }


    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description,
                isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }


    public String formatDate() {

        if (by.getHour() == 0 && by.getMinute() == 0) {
            return by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return this.saveDate();
        }

    }


    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s",
                super.description,
                saveDate(),
                super.isDone ? "1" : "0");
    }

    public String saveDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return by.format(formatter);
    }
}
