public class DeadlineTask extends Task{
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public DeadlineTask(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.type = "D";
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(by: "
                + this.time + ")";
    }
}
