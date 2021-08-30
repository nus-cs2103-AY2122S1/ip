package duke.tasks;
/**
 * Represent a task with a deadline.
 */
public class Deadline extends TaskWithDate {
    public Deadline(String description, String by, boolean done) {
        super(description,by);
        this.isDone = done;
    }

    public Deadline(String description, String by) {
        super(description,by);
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}