public class Event extends Task{

    private String duration;

    Event(String taskName, String duration) {
        super(taskName);
        this.duration = duration;
    }

    public String displayTask() {
        return "[E]" + super.displayTask() + " (at: " + duration + ")";
    }
}
