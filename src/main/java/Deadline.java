public class Deadline extends Task{
    protected String by;

    // dummy constructor for Jackson
    public Deadline() {
        super();
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    // getters & setters (needed for jackson)
    protected void setBy(String by) {
        this.by = by;
    }
    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + by + ")";
    }
}
