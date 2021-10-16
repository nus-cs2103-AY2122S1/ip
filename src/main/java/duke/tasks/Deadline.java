package duke.tasks;
/**
 * 
 * This represents a Deadline task with a time limit.
 * 
 * @author Rishabh Anand
 * @version CS2103, AY21/22 Semester 1
 * 
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    // returns the string representation of the Deadline
    @Override
    public String toString() {
        return "[D]" 
                + super.toString()
                + " (by: "
                + this.deadline
                + ")";
    }

    @Override
    public String toTextRepresentation() {
        String binaryStatus = this.isDone ? "1" : "0";
        return String.format("D; %s; %s; %s", binaryStatus, this.description.strip(), this.deadline.strip());
    }
}
