public class Event extends Task { //needs a start and end time, right now is just a string
    public String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "] [" + this.getStatusIcon() + "] " + description + " (" + time + ")";
    }
}
