package duke.domain;


import java.time.LocalDateTime;
import java.util.List;

import duke.constants.Constants;
import duke.shared.DateHelpers;
import duke.shared.GenericHelpers;
import duke.shared.InvalidDateException;

/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {
    public static final String TYPE_STRING = "D";
    private LocalDateTime dueDate;

    /**
     * Creates incomplete deadline task with given task, and due date.
     * @param name Name of deadline.
     * @param dueDate String representing date task is due.
     */
    public Deadline(String name, String dueDate) throws InvalidDateException {
        super(name);
        this.dueDate = DateHelpers.parseDateString(dueDate);
    }

    /**
     * Creates deadline task with given task, completion status and due date.
     * @param name Name of deadline.
     * @param isDone Whether deadline is to be marked complete upon creation.
     * @param dueDate String representing date task is due.
     */
    public Deadline(String name, boolean isDone, String dueDate) throws InvalidDateException {
        super(name, isDone);
        this.dueDate = DateHelpers.parseDateString(dueDate);
    }

    /**
     * Returns whether the deadline's due date and the date represented by the given string fall on the same day.
     * @param dateString String representing a date.
     * @return Whether the deadline's due date and the date represented by the given string fall on the same day
     */
    public boolean isDueOn(String dateString) {
        return isDueOn(DateHelpers.parseDateString(dateString));
    }

    /**
     * Returns whether the deadline's due date and the given date fall on the same day.
     * @param dateTime A date.
     * @return Whether the deadline's due date and the given date fall on the same day
     */
    public boolean isDueOn(LocalDateTime dateTime) {
        return dueDate.toLocalDate().equals(dateTime.toLocalDate());
    }

    /**
     * Returns a Deadline object from a given array of fields. Effectively the inverse of storageFields.
     * @return A Deadline object.
     */
    public static Task generateFromString(String[] fields) {
        int isDoneInt = Integer.parseInt(fields[1]);
        boolean isDone = isDoneInt == 1;
        String name = fields[2];
        String dueDate = fields[3];
        return new Deadline(name, isDone, dueDate);
    }

    @Override
    public List<String> getStorageFields() {
        List<String> fields = super.getStorageFields();
        fields.add(Constants.Input.DATETIME_FORMATTER.format(dueDate));
        return fields;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (by: %s)", base, Constants.Display.DATETIME_FORMATTER.format(dueDate));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            return this.getName().equals(d.getName()) && this.dueDate.equals(d.dueDate);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return GenericHelpers.combineHashCodes(super.hashCode(), dueDate.hashCode());
    }
}
