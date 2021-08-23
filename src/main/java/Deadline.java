public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException1 {
        super(description);
        if(description.equals("deadline")) {
            throw new DukeException1();
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
