/**
 * Encapsulates a task that has a description and takes place at a specific time.
 *
 * @author Clifford
 */

public class Event extends Task {
    protected String at;
    protected final static String taskSymbol = "[E]";

    public Event(String description, String at) throws IllegalArgumentException {
        super(description, taskSymbol);
        if (at.equals("")) {throw new IllegalArgumentException();}
        this.at = at;
    }

    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}