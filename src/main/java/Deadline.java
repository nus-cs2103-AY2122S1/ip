public class Deadline extends Task {
    private String time = "unknown";

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + time + ")";
    }
}
