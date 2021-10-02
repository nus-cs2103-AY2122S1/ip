package task;

import java.util.ArrayList;

/**
 * A type of task that requires a deadline that extends from Task
 *
 * @author: Wei Yangken
 */
public class Deadline extends Task {

    private static final String TASK_CAT = "deadline";
    private String deadline;
    private ArrayList<Tag> tags;

    /**
     *  Constructor to create an DEADLINE task
     * @param name Name of task
     * @param deadline Deadline of task
     */
    public Deadline(String name, String deadline, boolean isDone) {
        super(name, TASK_CAT, isDone);
        this.deadline = deadline.trim();
        this.tags = new ArrayList<>();
    }

    public Deadline(String name, String deadline, boolean isDone, ArrayList<Tag> tags) {
        super(name, TASK_CAT, isDone, tags);
        this.deadline = deadline.trim();
    }

    /**
     * Returns the name of the task in a format that shows type of task and its completion status
     * @return Task as a formatted string
     */
    @Override
    public String toString() {
        if (this.isDone()) {
            return String.format("[D][X] %s (by: %s)", this.getName(), this.deadline);
        } else {
            return String.format("[D][ ] %s (by: %s)", this.getName(), this.deadline);
        }
    }

    /**
     * @return Gets formatted deadline of task
     */
    @Override
    public String getDetail() {
        return "/by " + this.deadline;
    }

    /**
     * @return deadline of task in raw form
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * @param o Object to be compared to
     * @return Whether the tasks share the same name and deadline
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline task = (Deadline) o;
        // Compare the data members and return accordingly
        return this.getName().equals(task.getName())
                && this.deadline.equals(task.getDeadline());
    }
}
