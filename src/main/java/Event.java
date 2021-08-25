public class Event extends Task{

    private String duration;

    Event(String taskName, String duration, boolean status) {
        super(taskName, status);
        this.duration = duration;
    }

    public String displayTask() {
        return "E " + super.displayTask() + "| " + duration;
    }
}
