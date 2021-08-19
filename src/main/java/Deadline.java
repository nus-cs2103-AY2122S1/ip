public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String time = this.time .length() > 0 ? (" (by: " + this.time + ")") : "";

        return "[D]" + super.toString() + time;
    }
}
