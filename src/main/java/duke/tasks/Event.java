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

    /**
     * Returns a String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String timeInfoString = timeInfo.format(outputFormatter);
        return String.format("[E]%s (at: %s)", super.toString(), timeInfoString);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj Object to compare to
     * @return boolean indicating whehter the other object is "equal" to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event tmp = (Event) obj;
            return super.equals(obj) && timeInfo.equals(tmp.timeInfo);
        } else {
            return false;
        }
    }
}
