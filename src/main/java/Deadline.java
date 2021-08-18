class Deadline extends Task {
    protected String by;
    Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}