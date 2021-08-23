/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Deadline extends Task{
    /** The name of the deadline task.*/
    protected String by;

    /**
     * Constructs a Deadline.
     * @param name The name of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a simplified representation of the Deadline for easier recovery from save file.
     * @return The file formatted string representation of the Deadline.
     */
    public String toFileFormat() {
        return String.format("D%s,%s,%b", name, by, isDone);
    }

    /**
     * Returns a string representation of the Deadline, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
