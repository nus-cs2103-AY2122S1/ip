public class Deadline extends Task {

    public Deadline(String description, String date, boolean done) throws DukeException {
        super(description, date, done);
    }

    @Override
    public String toFileData() {
        return "D," + super.toFileData();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), super.getDateString());
    }
}
