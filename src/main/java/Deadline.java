public class Deadline extends Task {
    private String dueBy;

    public Deadline(String deadlineName) {
        super(deadlineName.substring(9, deadlineName.indexOf("/by ")));
        int start = deadlineName.indexOf("/by ");
        this.dueBy = deadlineName.substring(start + 4);
    }

    public Deadline(String deadlineName, boolean isDone) {
        super(deadlineName.substring(0, deadlineName.indexOf("(by:")), isDone);
        this.dueBy = deadlineName.substring(deadlineName.indexOf("(by:") + 5,
                deadlineName.length() - 1);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: "
                + this.dueBy
                + ")";
    }
}
