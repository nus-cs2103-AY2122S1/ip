public class Deadline extends Task {
    protected String timeDue;

    public Deadline(String description, String timeDue) {
        super(description);
        this.timeDue = timeDue;
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[D]" + description + " (by: " + this.timeDue + ")";
    }
}
