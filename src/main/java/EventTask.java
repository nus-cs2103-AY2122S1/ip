public class EventTask extends Task{
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public EventTask(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.type = "E";
        this.time = time;
    }


    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + "(at: "
                + this.time + ")";
    }
}

