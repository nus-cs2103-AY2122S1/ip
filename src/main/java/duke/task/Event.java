package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.Parser;
import duke.exception.DukeException;

/**
 * A task that has a starting time and date, and an ending time and date.
 */
public class Event extends Task {
    private final LocalDateTime dateAndTime;
    private final LocalTime endTime;

    /**
     * Initialises a new instance of Event.
     *
     * @param taskDescription The description of the event.
     * @param dateAndTime The starting time and date.
     * @param endTime The ending time and date.
     * @param isDone Marks the event as done or not done.
     */
    public Event(String taskDescription, LocalDateTime dateAndTime, LocalTime endTime, boolean isDone) {
        super(taskDescription, isDone);
        this.dateAndTime = dateAndTime;
        this.endTime = endTime;
    }

    /**
     Returns a new instance of Event with updated values.
     *
     * @param taskDescription The description of the updated Event.
     * @param dateAndTime The date and time of the updated Event.
     * @return A Event with the updated description or time, or both.
     * @throws DukeException If the given date and time is invalid.
     */
    public Event update(String taskDescription, String dateAndTime) throws DukeException {
        try {
            String updatedDescription = taskDescription == null
                    ? this.taskDescription
                    : taskDescription;
            LocalDateTime updatedTime = this.dateAndTime;
            LocalTime updatedEndTime = this.endTime;

            if (dateAndTime != null) {
                String[] splitEndTime = dateAndTime.split(" - ");
                updatedTime = Parser
                        .formatDateTime(splitEndTime[0]);
                boolean hasInputAfterDash = splitEndTime.length > 1
                        && splitEndTime[1].trim().length() > 0;

                if (hasInputAfterDash) {
                    updatedEndTime = LocalTime.parse(splitEndTime[1]);
                } else {
                    throw new DukeException("☹ OOPS!!! Please provide an end "
                            + "time for the event.");
                }
            }
            return new Event(updatedDescription,
                    updatedTime, updatedEndTime, this.isDone);

        } catch (DukeException de) {
            throw new DukeException(de.getMessage());
        }
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        String dateAndTime = DateTimeFormatter.ofPattern("dd MMM yyyy 'from' h:mm a")
                .format(this.dateAndTime);
        String endTime = DateTimeFormatter.ofPattern("h:mm a").format(this.endTime);
        if (super.isDone) {
            return "[E][X] " + super.taskDescription + " (at: " + dateAndTime + " - " + endTime + ")";
        } else {
            return "[E][ ] " + super.taskDescription + " (at: " + dateAndTime + " - " + endTime + ")";
        }
    }

    /**
     * Returns a String representation of the task to store within the save file.
     *
     * @return The String representation of the task.
     */
    @Override
    public String parseToString() {
        String result = super.isDone ? "X" : "0";
        result += "E/" + super.taskDescription + "/" + this.dateAndTime.toString() + "/"
                + this.endTime.toString();
        return result;
    }
}
