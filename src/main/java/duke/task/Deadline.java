package duke.task;

import java.time.LocalDateTime;

import duke.util.DukeDate;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Extends the Task Class which where it is a task that needs
 * to be done before a specific time
 *
 * @author Keith Tan
 */
public class Deadline extends Task {

    private DukeDate dueDate;

    /**
     * Constructor for Deadline
     * Task that contains a deadline to accomplish the task by
     */
    public Deadline(String description, DukeDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Getter that returns the deadline of task
     *
     * @return DukeDate returns the deadline of the task
     *
     */
    public DukeDate getDeadline() {

        return this.dueDate;

    }

    /**
     * Setter that sets the end date time of the event
     *
     */
    public void changeEndDateTime(LocalDateTime newEndDateTime) {

        this.dueDate.setEndTime(newEndDateTime);

    }

    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        String dateTime = this.dueDate.getEndTimeString();
        return "[" + "D" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription() + " "
                + "(by: " + dateTime + ")";
    }

}
