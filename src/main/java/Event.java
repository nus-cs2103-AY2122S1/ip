public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static Event splitEvent(String input) {
        String[] partsOfEvent = input.split("/at ");
        String eventContent = partsOfEvent[0].substring(6);
        String at = partsOfEvent[1];
        return new Event(eventContent, at);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + this.at
                + ")";    }
}
