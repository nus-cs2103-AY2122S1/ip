public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[D]" + checkBox + description + "(by: " + by + ")";
    }

}
