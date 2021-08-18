public class Event extends Task {
    public Event(String description) {
        super(description);
        int index = description.indexOf("/at");
        this.description = description.substring(0, index) + "(at:" + description.substring(index + 3) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
