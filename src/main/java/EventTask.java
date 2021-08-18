public class EventTask extends Task{
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public EventTask(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "E";
    }
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                + this.time + ")";
    }
}

