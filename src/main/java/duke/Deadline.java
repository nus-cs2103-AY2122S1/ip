package duke;

class Deadline extends Task { //Need to be done by deadline

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + by + ")";
    }
}
