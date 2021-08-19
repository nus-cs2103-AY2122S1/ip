public class Event extends Task{
    private String at;

    public Event (String taskDetails, String at) {
        super(taskDetails);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}
