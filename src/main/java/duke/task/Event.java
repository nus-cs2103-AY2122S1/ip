package duke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates an Event
 * (a task that happens at a specific time)
 * e.g. team project meeting at 2pm.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Event extends Task {
    private final String time;

    /**
     * Instantiates a new Event.
     *
     * @param name the title of the event.
     * @param time the time of the event.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    /**
     * Gets the time of the deadline task in Date form.
     *
     * @return Date of event task,
     * null if time does not match specified simple date format.
     */
    public Date getDate() {
        DateFormat inputFormat;
        Date date;

        if (time.contains(",")) {
            inputFormat = new SimpleDateFormat("dd MMM yyyy, h.mm aa");
        } else {
            inputFormat = new SimpleDateFormat("dd MMM yyyy");
        }
        try {
            date = inputFormat.parse(time);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatToSave() {
        return String.format("E | %s | %s", super.formatToSave(), time);
    }

    /**
     * String representation of the event.
     *
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Event) {
            Event e = (Event) o;
            return e.getTaskName().equals(this.getTaskName()) && e.time.equals(this.time);
        }
        return false;
    }
}
