package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import duke.exception.InvalidDukeCommandException;

/**
 * A class that extends Task, that allows for information about the date of occurrence of the task to be stored as well.
 */
public class Event extends Task {
    protected LocalDate eventTime;

    /**
     * Constructs a new instance of an Event with the given description of the task and the string literal of the event
     * date. If the date specified is invalid, an InvalidDukeCommandException is thrown instead.
     *
     * @param taskDescription a brief description of the task.
     * @param dateString a string literal of the due date, in the yyyy-mm-dd format.
     * @throws InvalidDukeCommandException if the string literal dateString given does not follow the yyyy-mm-dd format.
     */
    public Event(String taskDescription, String dateString) throws InvalidDukeCommandException {
        super(taskDescription);
        try {
            int[] dateArgs = Arrays.stream(dateString.split("-")).mapToInt(Integer::valueOf).toArray();
            this.eventTime = LocalDate.of(dateArgs[0], dateArgs[1], dateArgs[2]);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException("duke.Event date has to be declared in the format yyyy-mm-dd.");
        }
    }

    /**
     * Returns a string that represents a serialized store format of the task that is specific to Duke.
     *
     * @return a string of serialized format.
     */
    @Override
    public String toDukeStoreFormat() {
        return String.format("E | %s | %s", super.toDukeStoreFormat(), eventTime);
    }

    /**
     * Returns a string that shows the details of the task in a standardized format.
     *
     * @return a string of task details.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(),
                this.eventTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
