public class Event extends Task {
    private String timeDue;

    public Event(String s) {
        super(s.substring(0, s.indexOf("/at ")), TypeIndicators.EVENT);
        this.timeDue = s.substring(s.indexOf("/at ") + 4);
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