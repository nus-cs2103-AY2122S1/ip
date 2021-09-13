package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeIncorrectTaskDescription;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Represents an Event object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Event extends Task {
    private LocalDate dateOfEvent;

    /**
     * Constructor for the Event task.
     * @param arrString user input sliced into an array.
     * @throws DukeException if Duke cannot create the Event object.
     */
    protected Event(String[] arrString) throws DukeException {
        super(arrString.length < 2 ? " " : arrString[0]);
        String date = arrString[1] == null ? " " : arrString[1].strip();
        try {
            this.dateOfEvent = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeIncorrectTaskDescription(this, new IllegalArgumentException());
        }

    }

    /**
     * String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " +
                this.dateOfEvent.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String saveToFile() {
        return "E | " + super.saveToFile() + "| " + this.dateOfEvent;
    }

    /**
     * Gets the date of the event.
     * @return date of the event.
     */
    public LocalDate getDate() {
        return this.dateOfEvent;
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return this.SMALLER;
        }

        if (task instanceof Deadline) {
            return this.GREATER;
        }

        @SuppressWarnings("unchecked") // task must be a deadline
        Event d = (Event) task;

        return this.getDate().compareTo(d.getDate());
    }

}
