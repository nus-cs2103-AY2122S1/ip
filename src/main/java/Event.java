public class Event extends Task {
    private String timestamp;

    public Event(String name, String timestamp) {
        super(name);
        this.timestamp = timestamp;
    }

    public Event(String name, boolean isDone, String timestamp) {
        super(name, isDone);
        this.timestamp = timestamp;
    }

    public static Event fromText(String text) throws DukeException {
        String[] eventDetails = text.split(" | ", 4);
        if (eventDetails.length < 4) {
            throw new DukeException("Cannot parse Event from text - not enough arguments supplied.");
        }
        boolean isDone = eventDetails[1] == "X";
        String name = eventDetails[2];
        String timestamp = eventDetails[3];
        return new Event(name, isDone, timestamp);
    }

    @Override
    public String toText() {
        String[] props = new String[]{"E", super.getStatusIcon(), super.getName(), this.timestamp};
        return String.join(" | ", props);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timestamp);
    }
}
