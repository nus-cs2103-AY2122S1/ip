import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
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
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(eventInputs[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            throw new Exception(
                    "Invalid datetime: " + eventInputs[1] + "\n" + "Please use format: YYYY-MM-DD HH:MM:SS");
        }

        return new Event(description, time);
    }

    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss"));

        return "[E]" + super.toString() + " (at: " + timeStr + ")";
    }
}
