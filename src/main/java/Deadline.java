public class Deadline extends Task {

    private String name;
    private boolean completed;
    private String by;

    public Deadline(String name, boolean completed, String by) {
        super(name, completed);
        this.by = by;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[D][X] " + name + " (by:" + by + ")";
        } else {
            return "[D][ ] " + name + " (by:" + by + ")";
        }
    }
}
