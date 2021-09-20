package luke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that inherits from Entry to encapsulate Events.
 */
public class Event extends Entry{

    private LocalDate event;

    /**
     * Constructor for Event.
     */
    Event() {
        super();
        this.event = LocalDate.now();
    }

    /**
     * Constructor for Event.
     *
     * @param task Entry Task to be saved.
     * @param event Event details.
     * @throws LukeException Error thrown if event format is incorrect.
     */
    Event(String task, String event) throws LukeException {
        super(task);
        try {
            this.event = LocalDate.parse(event);
        } catch (DateTimeParseException e) {
            this.event = LocalDate.now();
            throw LukeException.INVALID_DATE_FORMAT_EXCEPTION;
        }
    }

    /**
     * Overrides Object's toString method.
     *
     * @return String representing Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + "(at: " + this.event.format(formatter) + ")";
    }

    /**
     * Overrides Entry's saveString method.
     * Returns string to be saved representing the Event.
     *
     * @return String to be saved representing the Event.
     */
    @Override
    public String saveString() {
        return "E" + super.saveString() + "," + this.event;
    }

    /**
     * Returns true if Event is Empty.
     *
     * @return Boolean corresponding to Event's length.
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty() || !event.isAfter(LocalDate.now());
    }
}