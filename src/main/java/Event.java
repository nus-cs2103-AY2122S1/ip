public class Event extends Task {
    private final char TYPE_INDICATOR = 'E';
    private String timeDue;

    public Event(String s) {
        super(s.substring(0, s.indexOf("/at ")));
        this.timeDue = s.substring(s.indexOf("/at ") + 4);
        this.typeIndicator = TYPE_INDICATOR;
    }

    /**
     * Returns the string representation of a Event.
     *
     * @return A string describing the Event.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.timeDue);
    }
}