public class Event extends Task{
    private String at;

    public Event (String taskDetails, String at) {
        super(taskDetails);
        this.at = at;
    }

    public String taskType() {
        return "E";
    }

    @Override
    public String getTaskDetails() {
        return super.getTaskDetails() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
