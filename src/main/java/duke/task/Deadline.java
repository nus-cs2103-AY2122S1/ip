package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.general.TaskType;

/**
 * Deadline encapsulates the name of the deadline as well as
 * when the task needs to be completed by.
 */
public class Deadline extends Task {
    private LocalDate doneBy;
    private String by;

    /**
     * Constructs duke.task.Deadline object with given name, and deadline of the task.
     * @param name The name of the duke.task.Deadline
     * @param by duke.task.Deadline of the task
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
        this.doneBy = LocalDate.parse(by);
    }

    /**
     * Getter for the type of task
     * @return String "D" for Deadline
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Getter for the Tasktype of task
     * @return Tasktype.DEADLINE
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Getter for the additional information on the Deadline
     * @return String with the deadline of the task
     */
    @Override
    public String getInfo() {
        return this.by;
    }

    /**
     * Makes a deep copy of itself
     * @return Deadline deep copy of itself
     */
    @Override
    public Task duplicate() {
        Task t = new Deadline(getName(), this.by);
        if (getCompletion().equals("X")) {
            t.toggleCompleted();
        }
        return t;
    }

    /**
     * Converts the deadline object into a string
     * @return A string containing the type (duke.task.Deadline), completion status and time
     * of the deadline to be done by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + doneBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
