package duke.task;

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
     */
    public Deadline(String task, String taskTime) {
        super(task);
        String[] dateAndTime = taskTime.split(" ");
        System.out.println(dateAndTime[0]);
        System.out.println(dateAndTime[1]);
        this.deadlineDate = LocalDate.parse(dateAndTime[0]);
        this.deadlineTime = LocalTime.parse(dateAndTime[1]);
    }

    /**
     * Constructor of Deadline. Including the information whether it's done or not.
     *
     * @param task The name of Deadline.
     * @param done Whether the task is done or not.
     * @param taskTime The time of Deadline.
     */
    public Deadline(String task, boolean done, String taskTime) {
        super(task, done);
        String[] dateAndTime = taskTime.split(" ");
        this.deadlineDate = LocalDate.parse(dateAndTime[0]);
        this.deadlineTime = LocalTime.parse(dateAndTime[1]);
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

    private String outputTaskTime() {
        return deadlineDate.getMonth().toString() + " " + deadlineDate.getDayOfMonth() + " " + deadlineDate.getYear();
    }
}
