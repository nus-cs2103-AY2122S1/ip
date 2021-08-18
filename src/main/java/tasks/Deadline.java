package tasks;

import exceptions.EmptyDeadlineBodyException;
import exceptions.InvalidDeadlineBodyException;

public class Deadline extends Task {
    private final String by;

    public Deadline(String deadlineDataText) throws EmptyDeadlineBodyException, InvalidDeadlineBodyException {
        if (deadlineDataText == null || deadlineDataText.isEmpty()) {
            throw new EmptyDeadlineBodyException();
        }
        String[] deadlineData = deadlineDataText.split("/by ", 2);
        if (deadlineData.length != 2 || deadlineData[0].isEmpty() || deadlineData[1].isEmpty()) {
            throw new InvalidDeadlineBodyException();
        }
        super.setDescription(deadlineData[0].trim());
        this.by = deadlineData[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
