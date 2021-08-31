package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidTimeStampException;

/** Class representing an event */
public class Event extends Task {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy' 'HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    private LocalDateTime timeInfo;

    /**
     * Event constructor.
     *
     * @param taskDetails Details about the task.
     * @param timeInfoString When the event occurs, to be given in d/M/YYYY HHmm format.
     * @throws InvalidTimeStampException If given timeInfoString is invalid.
     */
    public Event(String taskDetails, String timeInfoString) throws InvalidTimeStampException {
        super(taskDetails);
        try {
            this.timeInfo = LocalDateTime.parse(timeInfoString, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeStampException(timeInfoString);
        }
    }

    @Override
    public String toString() {
        String timeInfoString = timeInfo.format(outputFormatter);
        return String.format("[E]%s (at: %s)", super.toString(), timeInfoString);
    }

    @Override
    public String toDataString() {
        String timeInfoString = timeInfo.format(inputFormatter);
        return String.format("E | %s | %s", super.toDataString(), timeInfoString);
    }
}
