package duke.task;

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

    private DukeDate dueBy;

    /**
     * Constructor for Deadline
     * Task that contains a deadline to accomplish the task by
     */
    public Deadline(String description, DukeDate dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    /**
     * Getter that returns the deadline of task
     *
     * @return DukeDate returns the deadline of the task
     *
     */
    public DukeDate getDeadline() {

        return this.dueBy;

    }

    @Override
    public String toString() {
        String taskStatus = this.isCompleted() ? "X" : " ";
        String dateTime = this.dueBy.getEndTimeString();
        return "[" + "D" + "]"
                + "[" + taskStatus + "]"
                + " " + this.getDescription() + " "
                + "(by: " + dateTime + ")";
    }

}
