public class Deadline extends Task {
    private String timeDue;

    public Deadline(String title, String timeDue) {
        super(title, TypeIndicators.DEADLINE);
        this.timeDue = timeDue;
    }

    public Deadline(String title, String timeDue, boolean isDone) {
        super(title, TypeIndicators.DEADLINE);
        this.timeDue = timeDue;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return A string describing the Deadline.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.timeDue);
    }
}