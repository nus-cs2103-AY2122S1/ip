package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.IncompleteTaskDescriptionException;

/**
 * Deadline is a specific type of task that contains the description of the task.
 */
public class Deadline extends Task {
    private static final char TASK_LETTER = 'D';
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructs a deadline task.
     *
     * @param description The description of the task.
     * @param date The deadline date.
     * @param time The deadline time.
     * @param isDone Whether the deadline is done.
     */
    public Deadline(String description, LocalDate date, LocalTime time, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns string representation of a deadline.
     *
     * @return A string representing the deadline.
     */
    @Override
    public String toString() {
        String formattedDate = this.date.format(Task.DATE_TIME_FORMATTER);
        return String.format("[%c]%s (by: %s %s)", Deadline.TASK_LETTER,
                super.toString(), formattedDate, this.time);
    }

    /**
     * Converts the deadline to a string that can be saved to a file and converted back to itself.
     *
     * @return The string to be stored.
     */
    @Override
    public String stringToStore() {
        String formattedDate = this.date.format(Task.DATE_TIME_FORMATTER);
        return String.format("%c | %s | %s | %s | %s\n", Deadline.TASK_LETTER,
                this.getStatusIcon(), this.description, formattedDate, this.time);
    }

    /**
     * Creates a new deadline object.
     *
     * @param description The description of the task.
     * @param isDone Whether the deadline is done.
     * @return The deadline object created.
     * @throws IncompleteTaskDescriptionException If the description is empty or in incorrect format.
     */
    public static Deadline create(String description, boolean isDone) throws IncompleteTaskDescriptionException {
        if (description.matches("[^ ].* /by *[^ ].* [^ ].*")) {
            String deadlineSeparator = "/by";
            int separatorIndex = description.indexOf(deadlineSeparator);
            String taskDetail = description.substring(0, separatorIndex).trim();
            int len = deadlineSeparator.length();
            String[] by = description.substring(separatorIndex + len).trim().split(" ");
            try {
                LocalDate date = LocalDate.parse(by[0].trim());
                LocalTime time = LocalTime.parse(by[1].trim());
                return new Deadline(taskDetail, date, time, isDone);
            } catch (DateTimeParseException e) {
                throw new IncompleteTaskDescriptionException("deadline");
            }
        } else {
            throw new IncompleteTaskDescriptionException("deadline");
        }
    }
}
