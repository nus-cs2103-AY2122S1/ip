package duke.task;

import java.time.LocalDateTime;

import duke.util.DukeDate;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Extends the Event Class which where it is a task that start at a
 * specific time and ends at a specific time
 *
 * @author Keith Tan
 */
public class Event extends Task {

    private DukeDate duration;

    /**
     * Constructor for Event
     * Task that contains a duration to accomplish the task
     */
    public Event(String description, DukeDate duration) {
        super(description);
        this.duration = duration;

    }

    /**
     * Getter that returns the duration of the event
     *
     * @return DukeDate returns the duration of the event
     *
     */
    public DukeDate getDuration() {

        return this.duration;

    }

    /**
     * Setter that sets the start date time of the event
     *
     */
    public void changeStartDateTime(LocalDateTime newStartDateTime) {

        this.duration.setStartTime(newStartDateTime);

    }

    /**
     * Setter that sets the end date time of the event
     *
     */
    public void changeEndDateTime(LocalDateTime newEndDateTime) {

        this.duration.setEndTime(newEndDateTime);

    }

    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        return "[" + "E" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription() + " "
                + "(at: " + this.duration.toString() + ")";
    }
}
