public class Deadline extends Task {
    String by;
    public Deadline(String desc, String by) throws DukeException.EmptyDescriptionException {
        super(desc);
        this.by = by;
    }

    public Deadline(boolean isComplete, String desc, String by) throws DukeException.EmptyDescriptionException {
        super(isComplete, desc);
        this.by = by;
    }

    @Override
    public String getRepr() {
        return String.format("D|%s|%s", super.getRepr(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
