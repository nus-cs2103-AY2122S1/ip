/**
 * A class encapsulating a Event variant of a Task.
 *
 * @author Toh Wang Bin
 */
public class Events extends Task {

    //The duration during which the specified Task occurs.
    private String time;

    /**
     * Constructor for a Event instance.
     *
     * @param name A String describing the Task.
     * @oaram time A String describing the duration of the Task.
     */
    public Events(String name,String time) {
        super(name);
        this.time = time;
    }

    /**
     * Return the String representation of the Task instance.
     *
     * @return A String representing the Task instance.
     */
    @Override
    public String toString() {
        if (isDone) {
            return ("[E] [X] " + name  + " (at: " + time + ")");
        } else {
            return ("[E] [ ] " + name  + " (at: " + time + ")");
        }
    }
}
