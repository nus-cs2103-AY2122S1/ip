public class Deadline extends Task {

    protected String by;
    protected String status;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
        this.status = "[D]" + super.status + " (by: " + by + ")";
    }



}
