public class Event extends Task {
    private String timeDue;

    public Event(String s, String timeDue) {
        super(s, TypeIndicators.EVENT);
        this.timeDue = timeDue;
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