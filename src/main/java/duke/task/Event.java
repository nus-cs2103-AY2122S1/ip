package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.CommandParamException;

/**
 * This is an Event class that extends Task.
 */
public class Event extends TaskWithDateTime {

    /**
     * This is a Event constructor.
     *
     * @param description A String representing the description of the event.
     * @param at A String representing the date and time of the event.
     * @throws CommandParamException An exception thrown when the date and time is not formatted properly.
     */
    public Event(String description, String at) throws CommandParamException {
        super(description);
        assert !at.equals("") : "Event \"at\" field cannot be empty!";
        assert !description.equals("") : "Event \"description\" field cannot be empty!";
        String[] dateTime = at.trim().split(" ");
        // index 0 is date, 1 is time
        // date should be yyyy-mm-dd, time should be 2359 format
        if (dateTime.length != 2) { // guard clause to ensure proper dateTime format
            throw new CommandParamException("event");
        }
        if (dateTime[1].length() < 4) { // guard clause to ensure proper time format
            throw new CommandParamException("event");
        }
        try {
            String timeReformatted = dateTime[1].substring(0, 2) + ":" + dateTime[1].substring(2, 4);
            LocalDate date = LocalDate.parse(dateTime[0].trim());
            LocalTime time = LocalTime.parse(timeReformatted);
            this.date = date;
            this.time = time;
        } catch (DateTimeParseException e) {
            throw new CommandParamException("event");
        }
    }

    @Override
    public String fullCommand() {
        return "event " + this.description + " /at "
                + this.date.toString()
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("kkmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "
                + this.time.format(DateTimeFormatter.ofPattern("h:mma"))
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            if (!(o instanceof Event)) {
                return false;
            } else {
                Event other = (Event) o;
                return other.date.equals(this.date) && other.time.equals(this.time);
            }
        } else {
            return false;
        }
    }
}
