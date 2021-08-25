import java.time.LocalDateTime;

public class Deadline extends Task{

    private LocalDateTime deadline;

    /**
     * Constructor for Deadline task
     * @param name name of task
     * @param deadline Use to indicate the deadline of the task
     */
    public Deadline(String name, LocalDateTime deadline) {
           super(name);
           this.deadline = deadline;
    }


    /**
     * String representation of the Deadline class
     * @return String. See above
     */
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }

}
