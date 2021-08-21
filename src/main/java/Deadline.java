/**
 * Encapsulates a task that has a description and an end date
 *
 * @author Clifford
 */

public class Deadline extends Task {
    protected String by;
    protected final static String taskSymbol = "[D]";

    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description, taskSymbol);
        if (by.equals("")) {throw new IllegalArgumentException();}
        this.by = by;
    }

    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}