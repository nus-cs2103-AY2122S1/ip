package duke.task;

import java.time.LocalDateTime;

import duke.DateTime;

public class Event extends Task {
    protected LocalDateTime time;

    /**
     * Constructs a new Event object.
     *
     * @param description Event description.
     * @param time Event time.
     */
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a new Event object.
     *
     * @param description Event description.
     * @param time Event time.
     * @param isCompleted Event completion status.
     */
    public Event(String description, LocalDateTime time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Constructs a new Event object from an input string.
     *
     * @param input Input string used to create Event object.
     */
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
        LocalDateTime time = DateTime.parse(eventInputs[1]);

        return new Event(description, time);
    }

    @Override
    public String toString() {
        String timeStr = DateTime.stringify(this.time);

        return "[E]" + super.toString() + " (at: " + timeStr + ")";
    }
}
