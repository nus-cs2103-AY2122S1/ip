public class Deadline extends Task {

    private String by;
    public final String code = "D";

    public Deadline(String description, String by) throws DukeException{
        super(description);
        this.by = by;
    }

    public String getTimeAttr() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getCode() {
        return this.code;
    }
}