package duke.data.task;

import duke.data.exception.EmptyDescriptionException;
import duke.data.exception.EmptyTimeException;
import duke.data.exception.InvalidTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates event task
 */
public class Event extends Task {
    /** Time of the event */
    protected LocalDateTime at;

    /**
     * Constructor for Event
     * @param input the input array consisting of description and date/time
     * @throws EmptyDescriptionException if description is empty
     * @throws EmptyTimeException if time is empty
     * @throws InvalidTimeException if time is not in correct format
     */
    public Event(String[] input) throws EmptyDescriptionException, EmptyTimeException, InvalidTimeException {
        super(input[0]);
        if (input.length < 2) {
            throw new EmptyTimeException();
        }
        try {
            this.at = LocalDateTime.parse(input[1], DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }

    /**
     * Constructor for Event specifying isDone
     * @param input the input array consisting of description and date/time
     * @param isDone event's status from saved data
     */
    public Event(String[] input, boolean isDone) {
        super(input[0], isDone);
        this.at = LocalDateTime.parse(input[1]);
    }
    
    @Override
    public String convertToData() {
        return String.format("E/%s/%s/%s", this.isDone ? "1" : "0", this.description, this.at);
    }

    /**
     * Returns string representation of Event
     * @return string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
                + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + ")";
    }

}
