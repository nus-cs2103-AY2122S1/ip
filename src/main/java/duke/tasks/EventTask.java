package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Handles tasks that have a start and end date.
 */
public class EventTask extends Task {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Create a task based on the title, startDateTime and endDateTime.
     *
     * @param title single line description of the task
     * @param startDateTime start of the event.
     * @param endDateTime end of the event
     */
    public EventTask(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(title, Type.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Create a task with userInput by parsing to title and dates.
     *
     * @param inputString complete String input sent in by user.
     * @throws InvalidTaskException when the input cannot be parsed.
     */
    public static EventTask of(String inputString) throws InvalidTaskException {
        String[] userInput = inputString.split(" /at ");
        if (userInput.length != 2) {
            throw new InvalidTaskException("Expected '{title} /at {date}' for event tasks");
        }
        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            String[] arr = userInput[1].split(" - ");
            if (arr.length != 2) {
                DateParser.parseDateTimeInput(""); // throws error
            }
            startDate = DateParser.parseDateTimeInput(arr[0]);
            endDate = DateParser.parseDateTimeInput(arr[1]);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException(
                "The two dates for deadline creation could not be parsed. Expected:\n"
                    + "2 dates separated by ' - '. Dates come in the forms: "
                    + "'YYYY-MM-DD' or 'YYYY-MM-DD HHMM' (Time in 24hr format)."
            );
        }
        return new EventTask(userInput[0].trim(), startDate, endDate);
    }

    @Override
    public String taskToString() {
        String dates = String.format(
                "%s - %s",
                DateParser.toDatabaseFormat(this.startDateTime),
                DateParser.toDatabaseFormat(this.startDateTime)
        );
        return super.taskToString() + dates;
    }

    @Override
    public String toString() {
        return String.format(
                "[E] %s (at: %s - %s)",
                super.toString(),
                DateParser.toHumanReadable(startDateTime),
                DateParser.toHumanReadable(endDateTime)
        );
    }
}
