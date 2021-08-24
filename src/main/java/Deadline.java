/**
 * The Deadline class encapsulates all the details of each deadline.
 */

public class Deadline extends Task {
    private final String endTime;

    public Deadline(String message, String endTime){
        super(message);
        this.endTime = endTime;
    }

    /**
     * Overrides toString() method.
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + endTime + ")";
    }

    /**
     * Converts contents to a storable String.
     *
     * @return a String that represents this Event in storage
     */
    @Override
    public String toStorage() {
        return "D|" + super.toStorage() + "/by " + this.endTime;
    }
}
