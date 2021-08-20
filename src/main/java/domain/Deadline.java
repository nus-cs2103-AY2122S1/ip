package domain;

import java.util.List;
import java.time.LocalDateTime;

import constants.Constants;
import shared.DateHelpers;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {
    public static final String TYPE_STRING = "D";
    private LocalDateTime dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        // typeString = TYPE_STRING;
        this.dueDate = DateHelpers.parseDateString(dueDate);
    }

    public Deadline(String name, boolean isDone, String dueDate) {
        super(name, isDone);
        // typeString = TYPE_STRING;
        this.dueDate = DateHelpers.parseDateString(dueDate);
    }

    public boolean isDueOn(String dateString) {
        return isDueOn(DateHelpers.parseDateString(dateString));
    }

    public boolean isDueOn(LocalDateTime dateTime) {
        return dueDate.toLocalDate().equals(dateTime.toLocalDate());
    }

    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        String dueDate = fields[3];
        return new Deadline(name, isDone, dueDate);
    }

    @Override
    public List<String> storageFields() {
        List<String> fields = super.storageFields();
        fields.add(Constants.Input.DATETIME_FORMATTER.format(dueDate));
        return fields;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (by: %s)", base, Constants.Display.DATETIME_FORMATTER.format(dueDate));
        return result;
    }
}
