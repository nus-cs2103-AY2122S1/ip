package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that occurs on a specified date.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Event extends Task {
    /**
     * A constructor for an Event that specifies whether or not it is completed.
     * @param title a String representing the title of the event
     * @param timeDue a LocalDate representing time at which the event is due
     */
    public Event(String title, LocalDate timeDue) {
        super(title, TypeIndicator.EVENT);
        this.timeDue = timeDue;
    }

    /**
     * A constructor for an Event that specifies whether or not it is completed.
     * @param title a String representing the title of the event
     * @param timeDue a LocalDate representing time at which the event is due
     * @param isDone a boolean representing whether or not the event is completed
     */
    public Event(String title, LocalDate timeDue, boolean isDone) {
        super(title, TypeIndicator.EVENT);
        this.timeDue = timeDue;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a Event.
     *
     * @return A string describing the Event.
     */
    @Override
    public String toString() {
        String formattedTimeDue = timeDue == null
                ? ""
                : timeDue.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + String.format(" (at: %s)", formattedTimeDue);
    }
}