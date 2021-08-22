public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    public Deadline(String description, String by, boolean isDone) {
        super(description,
                isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }


    @Override
    public String saveString() {
        return String.format("D|%s|%s|%s",
                super.description,
                this.by,
                super.isDone ? "1" : "0");
    }
}
