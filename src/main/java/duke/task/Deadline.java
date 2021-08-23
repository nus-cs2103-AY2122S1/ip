package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    
    private final String identifier = "D";
    private LocalDateTime deadlineDateTime;
    private boolean isDateAndTime;
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        isDateAndTime = false;
        this.deadlineDate = LocalDate.parse(deadline);
    }

    public Deadline(String description, String deadlineDate, String deadlineTime) throws DateTimeParseException {
        super(description);
        this.deadlineDate = LocalDate.parse(deadlineDate);
        this.deadlineTime = LocalTime.parse(deadlineTime);
        isDateAndTime = true;
        this.deadlineDateTime = LocalDateTime.of(this.deadlineDate, this.deadlineTime);
    }

    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += (isDateAndTime
                ? " (by: " + this.deadlineDateTime.format(super.dateTimePattern) + ")"
                : " (by: " + this.deadlineDate.format(super.datePattern) + ")");
        return result;
    }

    @Override
    public String databaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|";
        result += isDateAndTime ? (this.deadlineDate + "|" + this.deadlineTime) : this.deadlineDate;
        return result;
    }
}
