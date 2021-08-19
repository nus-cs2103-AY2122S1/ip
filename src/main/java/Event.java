public class Event extends Task{
    private String eventDatetime;
    private static final String EVENT_DELIMITER = "/at";
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command. Use 'event <text> /at <datetime>'";

    private Event(String text, String eventDatetime) {
        super(text);
        this.eventDatetime = eventDatetime;
    }

    public static Event newEvent(String input) throws DukeException {
        String[] eventInfo = input.split(EVENT_DELIMITER);
        if (eventInfo.length < 2) {
            throw new DukeException(INVALID_EVENT_MESSAGE);
        }
        String event = eventInfo[1].trim();
        String eventText = eventInfo[0].trim();
        return new Event(event, eventText);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDatetime);
    }
}
