public class Event extends Task{
    public String eventTime;

    public Event (String task, String eventTime) {
        super(task);
        this.eventTime = eventTime;
    }

    public Event (String task, boolean done, String eventTime) {
        super(task, done);
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

    @Override
    public String toStoredString() {
        int finished = done ? 1 : 0;
        return "E | " + finished + " | " + taskName + " | " + eventTime;
    }
}
