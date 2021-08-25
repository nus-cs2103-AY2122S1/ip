public class Event extends Task{
    private String eventDatetime;
    private static final String EVENT_DELIMITER = "/at";
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command. Use 'event <text> /at <datetime>'";
    private static final String MISSING_EVENT_MESSAGE = "Some arguments are missing. Use 'event <text> /at <datetime>'";
    private static final String INVALID_SAVE_MESSAGE = "Event save is given in the wrong format";

    public static final char SYMBOL = 'E';

    private Event(String text, String eventDatetime, boolean isDone) {
        super(text, isDone);
        this.eventDatetime = eventDatetime;
    }

    public static Event newEvent(String input, boolean isDone) throws DukeException {
        if (input.split(" ").length < 3) {
            throw new DukeException(MISSING_EVENT_MESSAGE);
        }

        String[] eventInfo = input.split(EVENT_DELIMITER);
        if (eventInfo.length < 2) {
            throw new DukeException(INVALID_EVENT_MESSAGE);
        }
        String event = eventInfo[1].trim();
        String eventText = eventInfo[0].trim();
        return new Event(eventText, event, isDone);
    }

    public static Event newEventFromSave(String input) throws DukeException {
        String[] inputArr = input.split("\\|");
        if (inputArr.length != 3) {
            throw new DukeException(INVALID_SAVE_MESSAGE);
        }
        String isDone = inputArr[0].trim();
        String eventText = inputArr[1].trim();
        String event = inputArr[2].trim();
        return new Event(eventText, event, isDone.equals("1"));
    }

    public String getSaveFormat() {
        return String.format("%c | %d | %s | %s", SYMBOL, super.getDoneInt(), this.getText(), this.eventDatetime);
    };

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDatetime);
    }
}
