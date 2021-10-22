package back;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate date;

    /**
     * Event constructor.
     *
     * @param taskName Name of event.
     * @param at Date of event.
     */
    public Event(String taskName, LocalDate at) {
        super(taskName);
        this.date = at;
    }

    /**
     * Returns if the date of this object matches then input date.
     * @param theirDate LocalDate object to be compared
     * @return boolean true if the LocalDate provided is the same.
     */
    public boolean queryIfDateEquals(LocalDate theirDate) {
        return theirDate.equals(date);
    }

    /**
     * Returns a string representation of this event task.
     */
    @Override
    public String toString() {
        return super.toString() + " (EVENT occurs on: " + Ui.printDate(date) + ")";
    }

    /**
     * Returns if the object provided is the same as this.
     * @param o Object to be queried.
     * @return True if the object provided is the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            boolean nameEquals = e.taskName.equals(taskName);
            boolean donenessEquals = e.isDone == isDone;
            boolean dateEquals = e.date.equals(date);
            return (nameEquals && donenessEquals && dateEquals);
        } else {
            return false;
        }
    }
}
