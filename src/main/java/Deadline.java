public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, boolean done) { super(description, done); }

    @Override
    public String toFileData() {
        return "D," + super.toFileData();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
