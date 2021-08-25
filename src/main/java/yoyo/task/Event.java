package yoyo.task;

import yoyo.utility.Separator;

import java.time.LocalDateTime;

/**
 * A subclass of duke.task.Task of event type.
 */
public class Event extends Task {
    private LocalDateTime datetime;

    public Event(String name, LocalDateTime datetime) {
        super(name);
        this.datetime = datetime;
    }

    public Event(String name, LocalDateTime datetime, boolean isDone) {
        super(name, isDone);
        this.datetime = datetime;
    }

    /**
     * Shows all statuses appended with name.
     *
     * @return A status string containing name.
     */
    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (by: " + datetime.toString().replace('T', ' ') + ")";
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    @Override
    public String showStatusWrite() {
        return this.printType() + this.printCompletionStatus()
                + Separator.SEPARATOR + this.name
                + Separator.SEPARATOR + this.datetime;
    }

    @Override
    public String printType() {
        return "[E]";
    }

    @Override
    public boolean equals(Object o) {
        @SuppressWarnings("unchecked")
        Event otherEvent = (Event) o;
        return this.isDone == otherEvent.isDone
                && this.name.equals(otherEvent.name)
                && this.datetime.equals(otherEvent.datetime);
    }

}
