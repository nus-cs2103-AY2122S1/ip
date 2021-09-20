package duke.task;

import duke.exception.InvalidTaskException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a special type of task, which contains the deadline date and time.
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Constructor of Deadline.
     *
     * @param task The name of Deadline.
     * @param taskTime The time of Deadline.
     * @throws InvalidTaskException Throws exception if input is not valid.
     */
    public Deadline(String task, String taskTime) throws InvalidTaskException {
        super(task);
        String[] dateAndTime = taskTime.split(" ");
        if (dateAndTime.length == 2) {
            try {
                this.deadlineDate = LocalDate.parse(dateAndTime[0]);
                this.deadlineTime = LocalTime.parse(dateAndTime[1]);
            } catch (DateTimeException dateTimeException) {
                throw new InvalidTaskException("Deadline");
            }
        } else {
            throw new InvalidTaskException("Deadline");
        }
    }

    /**
     * Constructor of Deadline.
     *
     * @param task The name of Deadline.
     * @param isDone Indicate whether the task has been done or not.
     * @param taskTime The time of Deadline.
     * @throws InvalidTaskException Throws exception if input is not valid.
     */
    public Deadline(String task, boolean isDone, String taskTime) throws InvalidTaskException {
        super(task, isDone);
        String[] dateAndTime = taskTime.split(" ");
        if (dateAndTime.length == 2) {
            try {
                this.deadlineDate = LocalDate.parse(dateAndTime[0]);
                this.deadlineTime = LocalTime.parse(dateAndTime[1]);
            } catch (DateTimeException dateTimeException) {
                throw new InvalidTaskException("Deadline");
            }
        } else {
            throw new InvalidTaskException("Deadline");
        }
    }

    /**
     * Returns the String representation of the deadline task.
     *
     * @return The String representation of the deadline task.
     */
    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[D]" + "[" + finished + "] " + this.getTaskName() + " (by: " + this.outputTaskTime() + ", "
                + deadlineTime.toString() + ")";
    }

    /**
     * Returns the representation of the deadline task when it is stored.
     *
     * @return the representation of the deadline task when it is stored.
     */
    @Override
    public String toStoredString() {
        int finished = this.isDone() ? 1 : 0;
        return "D | " + finished + " | " + this.getTaskName() + " | " + deadlineDate + " " + deadlineTime;
    }

    /**
     * Compares this object with a given object.
     *
     * @param comparedObject The object compared with this object.
     * @return Returns true if they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object comparedObject) {
        if (!(comparedObject instanceof Deadline)) {
            return false;
        }
        Deadline comparedDeadlineTask = (Deadline) comparedObject;
        return comparedDeadlineTask.toString().equals(this.toString());
    }

    private String outputTaskTime() {
        return deadlineDate.getMonth().toString() + " " + deadlineDate.getDayOfMonth() + " " + deadlineDate.getYear();
    }
}
