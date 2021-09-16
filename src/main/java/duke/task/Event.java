package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.util.DukeException;

/**
 * Represents an event task with a <code>description</code> corresponding to the content
 * and a <code>at</code> time representing the event time.
 */
public class Event extends Task {
    protected LocalDateTime at;
    private Boolean hasTime = true;

    /**
     * Represents a new Event object.
     * @param description for Event
     * @param at time for Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = parseTime(at);
    }

    /**
     * Gets an At time of an Event object.
     *
     * @return at time of type LocalDateTime
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Parses a time String into a LocalDateTime.
     *
     * @param time a String of format dd/mm/yyyy hhmm(optional)
     * @return transformed time of type LocalDateTime
     */
    private LocalDateTime parseTime(String time) {
        String[] str = time.split(" ");
        String[] oldDate = str[0].split("/");
        if (oldDate.length != 3) {
            throw new DukeException("OOPS!!! The time is not of the correct format!");
        }
        LocalDateTime localTime;
        if (str.length > 1) {
            String hour = str[1].substring(0, 2);
            String min = str[1].substring(2, 4);

            localTime = LocalDateTime.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]),
                    Integer.parseInt(oldDate[0]), Integer.parseInt(hour), Integer.parseInt(min));
        } else {
            localTime = LocalDate.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]),
                    Integer.parseInt(oldDate[0]))
                    .atStartOfDay();
            hasTime = false;
        }
        return localTime;
    }


    @Override
    public String toString() {
        String string = "[E]" + super.toString() + " (at: ";
        if (hasTime) {
            string += at.format(DateTimeFormatter.ofPattern("HH:mm, dd/MM/yyyy")) + ")";
        } else {
            string += at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
        }
        return string;
    }

}

