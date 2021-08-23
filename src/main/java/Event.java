public class Event extends Task {
    private String timeDue;

    public Event(String title, String timeDue) {
        super(title, TypeIndicators.EVENT);
        this.timeDue = timeDue;
    }

    public Event(String title, String timeDue, boolean isDone) {
        super(title, TypeIndicators.EVENT);
        this.timeDue = timeDue;
        this.isDone = isDone;
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