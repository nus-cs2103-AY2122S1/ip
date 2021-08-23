public class Deadline extends DateTask {
    public Deadline(String description, String by) throws DukeException {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate() + ")";
    }

}
