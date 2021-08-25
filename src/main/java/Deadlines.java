/**
 * A class encapsulating a Deadline variant of a Task.
 *
 * @author Toh Wang Bin
 */
public class Deadlines extends Task {

    //The time by which the Task should be completed, i.e. the deadline.
    private String deadline;

    /**
     * Constructor for a Deadline instance.
     *
     * @param name A String describing the Task.
     * @param deadline A String describing the deadline of the Task.
     */
    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Return the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    @Override
    public String toString() {
        if (isDone) {
            return ("[D] [X] " + name + " (by: " + deadline + ")");
        } else {
            return ("[D] [ ] " + name + " (by: " + deadline + ")");
        }
    }

    public String toDataString() {
        StringBuilder str = new StringBuilder();
        if (isDone) {
            str.append("D|1|").append(super.name).append("|").append(deadline);
            return str.toString();
        } else {
            str.append("D|0|").append(super.name).append("|").append(deadline);
            return str.toString();
        }
    }
}
