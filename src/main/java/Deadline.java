public class Deadline extends Task {
    public Deadline(String description, String deadline) {
        super(String.format("%s (by: %s)", description, deadline));
    }

    public String getTaskIcon() {
        return "D";
    }
}
