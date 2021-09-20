package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.util.DukeException;

/**
 * Class that encapsulates an Event task.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Returns a new Event object.
     * @param eventName The user input.
     */
    public Event(String eventName) throws DukeException {
        super(eventName, 6, eventName.indexOf("/at") - 1);
        int start = eventName.indexOf("/at");
        this.eventDate = LocalDate.parse(eventName.substring(start + 4));
    }

    /**
     * Returns a new Event object.
     * @param eventName The Event as written in the save file.
     * @param isDone Whether the task is done.
     */
    public Event(String eventName, boolean isDone) {
        super(eventName, isDone, 0, eventName.indexOf("(at:") - 1);
        int start = eventName.indexOf("(at:") + 5;
        eventDate = LocalDate.parse(eventName.substring(start, start + 11),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate date) {
        this.eventDate = date;
    }

    /**
     * Overrides the toString method in Task.
     * @return The String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + getEventDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    /**
     * Overrides the equals method in Object.
     * @param o The Object to compare to.
     * @return If the objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            return e.getTaskName().equals(this.getTaskName())
                    && e.getEventDate().isEqual(this.getEventDate());
        }
        return false;
    }
}
