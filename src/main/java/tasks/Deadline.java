package tasks;

import exceptions.EmptyDeadlineBodyException;
import exceptions.InvalidDateTimeFormatException;
import exceptions.InvalidDeadlineBodyException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String deadlineDataText) throws EmptyDeadlineBodyException, InvalidDeadlineBodyException,
            InvalidDateTimeFormatException {
        if (deadlineDataText == null || deadlineDataText.isEmpty()) {
            throw new EmptyDeadlineBodyException();
        }
        String[] deadlineData = deadlineDataText.split("/by ", 2);
        if (deadlineData.length != 2 || deadlineData[0].isEmpty() || deadlineData[1].isEmpty()) {
            throw new InvalidDeadlineBodyException();
        }
        super.setDescription(deadlineData[0].trim());
        try {
            this.by = LocalDate.parse(deadlineData[1].trim());
        } catch (DateTimeParseException dte) {
            throw new InvalidDateTimeFormatException();
        }
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super.setDescription(description);
        super.setIsDone(isDone);
        this.by = by;
    }

    @Override
    public String getTaskRepresentation() {
        return TaskType.DEADLINE + "," + super.getTaskRepresentation() + this.by + ",";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.getMonth() + " " + by.getDayOfMonth() + " " + by.getYear() + ")";
    }
}
