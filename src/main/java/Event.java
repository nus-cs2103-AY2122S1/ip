public class Event extends Task{
    private final String dateTime;

    public Event(String description) {
        super(description.substring(0, description.indexOf("/at ") - 1));
        this.dateTime = description.substring(description.indexOf(" /at") + 5);
    }

    @Override
    public String getTaskInfo() {
        return "E" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }
}
