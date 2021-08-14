public class Event extends Task{
    private String taskType = "E";
    private String date;
    public Event(String event, String date) {
        super(event);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", this.taskType, super.toString(), this.date);
    }
}
