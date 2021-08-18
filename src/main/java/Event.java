public class Event extends Task{
    public String eventTime;
    public Event (String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.done) {
            finished = "X";
        }
        return "[E]" + "[" + finished + "] " + this.taskName + " (at: " + this.eventTime + ")";
    }
}
