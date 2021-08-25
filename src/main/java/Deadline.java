import java.io.IOException;

public class Deadline extends Task {

    protected String by;
    private static final String label = "D";

    public Deadline(String description, String by) throws DukeException, IOException {
        super(description);
        if (by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The deadline cannot be empty.");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + label + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return label + super.toDataString() + " | " + by;
    }

}
