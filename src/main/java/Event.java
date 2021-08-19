/**
 * Represents an Event object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Event extends Task {
    private final String startTime;

    protected Event(String[] arrString) throws DukeException {
        super(arrString.length < 2 ? " " : arrString[0]);
        this.startTime = arrString[1];
    }

    /**
     * String representation of the Event object
     */
    @Override
    public String toString() {
        String[] arrString = this.startTime.split(" ", 2);
        return "[E]" + super.toString() + "(" + arrString[0] + "at: " + arrString[1] + ")";
    }
}
