package task;

/**
 * Event class.
 *
 * This class is a Task that has an 'at' datetime String.
 */
public class Event extends Task {

    public final static String SPLIT_WORD = "at";

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String timing) {
        this.at = timing;
    }

    /**
     * Provides a formatted String of the 'at' field.
     *
     * @return formatted String for field 'at'
     */
    public String getFormattedAt() {
        return " (" + SPLIT_WORD + ": " + at + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getFormattedAt();
    }
}
