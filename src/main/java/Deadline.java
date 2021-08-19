public class Deadline extends Task {
    protected String ddlByTime;

    public Deadline(String deadlineName, String byTime) {
        super(deadlineName);
        this.ddlByTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ddlByTime + ")";
    }
}
