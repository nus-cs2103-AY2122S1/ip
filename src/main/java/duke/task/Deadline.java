package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.logic.DateTimeParser;

/**
 * A deadline is a task that has a specific deadline.
 */
public class Deadline extends Task {
    private final LocalDate dateOfDeadline;
    private final LocalTime timeOfDeadline;

    /**
     * Creates a new deadline object that has the given description and due on the given date.
     *
     * @param description          The description of the deadline
     * @param stringDateOfDeadline The due date/time string of the task
     */
    public Deadline(String description, String stringDateOfDeadline) {
        super(description, "D");
        DateTimeParser logicDateTimeParser = new DateTimeParser(stringDateOfDeadline);
        timeOfDeadline = logicDateTimeParser.getTime();
        dateOfDeadline = logicDateTimeParser.getDate();
    }

    /**
     * Creates a new deadline object that has the given description and due on the given date.
     *
     * @param description The description of the deadline
     * @param dateTime    The due date/time of the task
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, "D");
        dateOfDeadline = dateTime.toLocalDate();
        timeOfDeadline = dateTime.toLocalTime();
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(dateOfDeadline, timeOfDeadline);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s, %s)", dateOfDeadline, timeOfDeadline); // No preposition
    }

    @Override
    public String getDataLine() {
        return super.getDataLine() + " | " + dateOfDeadline + " " + timeOfDeadline;
    }
}
