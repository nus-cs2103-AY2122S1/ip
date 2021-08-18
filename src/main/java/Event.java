/**
 * Represents an Event object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Event extends Task {
    private final String startTime;

    protected Event(String[] arrString) {
        super(arrString[0]);
        if (arrString.length < 2) {
            throw new IllegalArgumentException("To indicate the date, please input \"/at {date}\" ");
        }
        this.startTime = arrString[1];
    }

    /**
     * String representation of the Event object
     */
    @Override
    public String toString() {
        String[] arrString = this.startTime.split(" ", 2);
        return "[E]" + super.toString() + "(" + arrString[0] + ": " + arrString[1] + ")";
    }
}
