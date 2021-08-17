public class Deadline extends Task{
    protected String ddl;

    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddl + ")";
    }
}
