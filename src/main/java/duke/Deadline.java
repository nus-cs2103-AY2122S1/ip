package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dateTime;
    public Deadline(String description, String dateTime) throws DukeException {
        super(description);
        this.dateTime = LocalDate.parse(dateTime);
    }
    public String toString() {
        String dateString = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(), description, dateString);
    }

    public String getDateTime() {
        return dateTime.toString();
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }
}
