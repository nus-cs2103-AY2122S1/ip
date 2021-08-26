public class Deadline extends Task{
    public Deadline(String description, String by) {
        super(description, "Deadline", by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
