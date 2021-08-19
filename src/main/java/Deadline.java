/**
 * Represents a Deadline object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Deadline extends Task {
    private final String deadline;

    protected Deadline(String[] arrString) throws DukeException {
        super(arrString.length < 2 ? " " : arrString[0]);
        this.deadline = arrString[1];
    }

    /**
     * String representation of the Deadline object
     */
    @Override
    public String toString() {
        String[] arrString = this.deadline.split(" ", 2);
        return "[D]" + super.toString() + "(" + arrString[0] + "by: " + arrString[1] + ")";
    }
}
