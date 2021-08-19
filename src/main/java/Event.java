public class Event extends Task{
    private String dateTime;

    public Event(String description) {
        super(description.substring(0, description.indexOf("/at ")));
        this.dateTime = description.substring(description.indexOf("/at ") + 4);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.dateTime + ")";
    }
}
