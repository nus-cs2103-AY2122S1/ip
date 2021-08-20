package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import constants.Constants;
import shared.DateHelpers;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        typeString = "D";
        this.dueDate = DateHelpers.parseDateString(dueDate);
    }

    public boolean isDueOn(String dateString) {
        return isDueOn(DateHelpers.parseDateString(dateString));
    }

    public boolean isDueOn(LocalDateTime dateTime) {
        return dueDate.toLocalDate().equals(dateTime.toLocalDate());
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (by: %s)", base, Constants.Display.DATETIME_FORMATTER.format(dueDate));
        return result;
    }
}
