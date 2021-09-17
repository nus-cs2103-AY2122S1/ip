package duke.task;

import java.time.LocalDate;

import duke.Storage;

public class Deadline extends Task {

    private LocalDate time;

    /**
     * Constructor for Deadline class
     *
     * @param taskName name of task
     * @param time due date of task
     */
    public Deadline(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Returns the string representation of the deadline
     *
     * @return string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName() + this.getTimeString();
    }

    /**
     * returns the string representation of the due date
     *
     * @return string representation of the due date
     */
    public String getTimeString() {
        return "(by: " + this.time.format(Storage.getFormatter()) + ")";
    }
}
