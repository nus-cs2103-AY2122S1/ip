public class Deadline extends Task {
    private String timeDue;

    public Deadline(String s, String timeDue) {
        super(s, TypeIndicators.DEADLINE);
        this.timeDue = timeDue;
    }

//    public Deadline createDeadlineWithDetail(String title, String timeDue, boolean isDone) {
//        Deadline deadline = new Deadline(title)
//    }

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