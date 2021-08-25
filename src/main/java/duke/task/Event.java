package duke.task;

public class Event extends Task{
    protected String eventTime;

    public Event(String name, String eventTime) { //Event class to handle Event task
        super(name);
        this.eventTime = TaskTime.convertDateTimeFormat(eventTime);
    }

    @Override
    public String write() {
            return "E " + super.write() + " | " + this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
