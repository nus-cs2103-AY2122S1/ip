package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final char TASK_LETTER = 'D';
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s %s)", Deadline.TASK_LETTER,
                super.toString(), this.date.format(Deadline.DATE_TIME_FORMATTER), this.time);
    }

    @Override
    public String stringToStore() {
        return Deadline.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.date.format(Deadline.DATE_TIME_FORMATTER) + " | " + this.time + "\n";
    }
}