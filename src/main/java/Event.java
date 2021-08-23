public class Event extends Task {

    protected String at;

    /**
     * Constructor for an Event.
     * @param description The description of th event.
     * @param at The location of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts the given Event into an appropriate format for txt file.
     * @return a String of the Event for input into a txt file.
     */
    @Override
    public String toTxt() {
        return String.format("E | %d | %s | %s", super.getIsDone() ? 1 : 0, super.getDescription(),
                this.at + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[E]"+ super.toString() + " (at: " + at + ")";
    }

}
