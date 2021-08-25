public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    public static Event fromInput(String input) throws Exception {
        String[] eventInputs = input.trim().split("\\s+/at\\s+", 2);

        if (eventInputs.length == 1) {
            if (eventInputs[0].length() == 0) {
                throw new Exception("Event must have description and time");
            } else {
                throw new Exception("Event must have time");
            }
        }

        if (eventInputs.length != 2) {
            throw new Exception("Event must have description and time");
        }

        String description = eventInputs[0];
        String time = eventInputs[1];

        return new Event(description, time);
    }

    @Override
    public String toString() {
        String time = this.time.length() > 0 ? (" (at: " + this.time + ")") : "";

        return "[E]" + super.toString() + time;
    }
}
