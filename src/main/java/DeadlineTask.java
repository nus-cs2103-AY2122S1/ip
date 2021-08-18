public class DeadlineTask extends Task{
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public DeadlineTask(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                + this.time + ")";
    }
}
