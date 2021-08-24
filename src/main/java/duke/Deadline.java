package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate date;
    protected LocalDateTime dateTime;

    public Deadline(String description, Object dateTime) {
        super(description);

        if (dateTime instanceof LocalDateTime) {
            this.dateTime = (LocalDateTime) dateTime;
        } else {
            this.date = (LocalDate) dateTime;
        }
    }

    @Override
    public String toString() {

        if (this.dateTime != null) {
            return String.format("[D]%s (by: %s)", super.toString(),
                    this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")));
        } else {
            return String.format("[D]%s (by: %s)", super.toString(),
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        }
    }
}
