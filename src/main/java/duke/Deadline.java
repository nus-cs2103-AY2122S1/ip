package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String description;
    protected boolean isDone;
    final String DEADLINE = "[D]";
    protected String dateAndTime;
    protected LocalDateTime localDateTime;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.isDone = false;
        this.dateAndTime = dateAndTime;
    }

    public void formatLocalDateTime() {
        if (this.dateAndTime.substring(0, 1).matches("[0-9]")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.localDateTime = LocalDateTime.parse(dateAndTime, dateTimeFormatter);
        } else {
            this.localDateTime = LocalDateTime.parse(dateAndTime, dtf);
        }
    }

    public String getDate() {
        return this.dateAndTime;
    }

    @Override
    public String toString() {
        formatLocalDateTime();
        return DEADLINE + this.getStatusIcon() + " " + this.getDescription() + " (by: " + localDateTime.format(dtf) + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Deadline) {
            Deadline deadline = (Deadline) object;
            return deadline.isDone == this.isDone && deadline.description.equals(this.description);
        }
        return false;
    }
}
