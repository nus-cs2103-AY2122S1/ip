public class Deadline extends Task {
    private String timeDue;

    public Deadline(String s) {
        super(s.substring(0, s.indexOf("/by ")), TypeIndicators.DEADLINE);
        this.timeDue = s.substring(s.indexOf("/by ") + 4);
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