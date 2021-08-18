public class Event extends Task{
    private String dateTime;
    public Event(String description, String dateTime){
        super(description);
        this.dateTime = dateTime;
    }
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatusIcon(), description, dateTime);
    }
}
