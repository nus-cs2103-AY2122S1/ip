// tasks that start at a specific time and ends at a specific time
public class Event extends Task {
    protected String description;
    protected String time;

    public Event(String description) {
        super(description);
        this.description = description;
    }

    public String getTask() {
        String[] splitted = description.split("/at ", 2);
        String text = splitted[0];
        time = splitted[1];
        return "[E]" + "[" + super.getStatusIcon() + "] " + text + "(at: " + time + ")";
    }
}
