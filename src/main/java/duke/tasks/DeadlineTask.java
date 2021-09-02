package duke.tasks;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

/**
 * Handles tasks that have a single date.
 */
public class DeadlineTask extends Task {

    private final LocalDateTime date;

    /**
     * Create new Deadline task.
     *
     * @param title description of the task
     * @param date date the task is to be completed by.
     */
    public DeadlineTask(String title, LocalDateTime date) {
        super(title, Type.DEADLINE);
        this.date = date;
    }

    /**
     * Create a task with userInput by parsing to title and dates.
     *
     * @param inputString complete String input sent in by user.
     * @throws InvalidTaskException when the input cannot be parsed.
     */
    public static DeadlineTask of(String inputString) throws InvalidTaskException {
        String[] args = inputString.split(" /by ");
        if (args.length != 2) {
            throw new InvalidTaskException("Expected '{title} /by {dates}' for deadline tasks");
        }
        LocalDateTime date;
        try {
            date = DateParser.parseDateTimeInput(args[1].trim());
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException(
                "Date for event creation could not be parsed. Expected:\n"
                    + "'YYYY-MM-DD' or 'YYYY-MM-DD HHMM' (Time in 24hr format)."
            );
        }
        return new DeadlineTask(args[0].trim(), date);
    }

    @Override
    public String taskToString() {
        return super.taskToString() + DateParser.toDatabaseFormat(this.date);
    }

    /**
     * Get a long number representing the urgency (date) of a task.
     *
     * @return numeric value to be used to compare tasks.
     */
    @Override
    protected long getUrgency() {
        return this.date.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return "[D] "
                + super.toString()
                + String.format(" (by: %s)", DateParser.toHumanReadable(this.date));
    }
}
