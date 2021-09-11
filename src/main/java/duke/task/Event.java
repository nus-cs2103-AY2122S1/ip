package duke.task;

import duke.exception.InvalidTaskException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a special type of task, which contains the time for the event.
 */
public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    /**
     * Constructor of Event.
     *
     * @param task The name of the event.
     * @param eventTime The time of the event.
     * @throws InvalidTaskException Throws exception if input is not valid.
     */
    public Event(String task, String eventTime) throws InvalidTaskException {
        super(task);
        String[] dateAndTime = eventTime.split(" ");
        if (dateAndTime.length == 4 && dateAndTime[2].equals("to")) {
            try {
                this.eventDate = LocalDate.parse(dateAndTime[0]);
                this.eventStartTime = LocalTime.parse(dateAndTime[1]);
                this.eventEndTime = LocalTime.parse(dateAndTime[3]);
            } catch (DateTimeException dateTimeException) {
                throw new InvalidTaskException("Event");
            }
        } else {
            throw new InvalidTaskException("Event");
        }
    }

    /**
     * Constructor of Event.
     *
     * @param task The name of the event.
     * @param isDone Indicate whether the event has been done or not.
     * @param eventTime The time of the event.
     * @throws InvalidTaskException Throws exception if input is not valid.
     */
    public Event(String task, boolean isDone, String eventTime) throws InvalidTaskException {
        super(task, isDone);
        String[] dateAndTime = eventTime.split(" ");
        if (dateAndTime.length == 4 && dateAndTime[2].equals("to")) {
            try {
                this.eventDate = LocalDate.parse(dateAndTime[0]);
                this.eventStartTime = LocalTime.parse(dateAndTime[1]);
                this.eventEndTime = LocalTime.parse(dateAndTime[3]);
            } catch (DateTimeException dateTimeException) {
                throw new InvalidTaskException("Event");
            }
        } else {
            throw new InvalidTaskException("Event");
        }
    }

    /**
     * Returns the String representation of the event task.
     *
     * @return The String representation of the event task.
     */
    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[D]" + "[" + finished + "] " + this.getTaskName() + " (at: " + outputTaskTime() + ", "
                + eventStartTime.toString() + " to " + eventEndTime.toString() +")";
    }

    /**
     * Returns the representation of the event task when it is stored.
     *
     * @return the representation of the event task when it is stored.
     */
    @Override
    public String toStoredString() {
        int finished = this.isDone() ? 1 : 0;
        return "E | " + finished + " | " + this.getTaskName() + " | " + eventDate + " " + eventStartTime
                + " to " + eventEndTime;
    }

    /**
     * Compares this object with a given object.
     *
     * @param comparedObject The object compared with this object.
     * @return Returns true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object comparedObject) {
        if (!(comparedObject instanceof Event)) {
            return false;
        }
        Event comparedEventTask = (Event) comparedObject;
        return comparedEventTask.toString().equals(this.toString());
    }

    private String outputTaskTime() {
        return eventDate.getMonth().toString() + " " + eventDate.getDayOfMonth() + " " +
                eventDate.getYear();
    }
}
