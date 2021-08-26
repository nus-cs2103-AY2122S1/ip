public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, boolean done) { super(description, done); }

    @Override
    public String toFileData() {
        return "D," + super.toFileData();
    }

    public Deadline(String description, String date) throws DukeException {
        super(description, date);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), super.getDateString());
    }
}
