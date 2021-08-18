public class Deadline extends Task {

    private String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.title + "(by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.title + "(by: " + this.deadline + ")";
        }
    }
}
