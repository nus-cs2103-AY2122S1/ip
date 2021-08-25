import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public static Deadline fromInput(String input) throws Exception {
        String[] deadlineInputs = input.trim().split("\\s+/by\\s+", 2);

        if (deadlineInputs.length == 1) {
            if (deadlineInputs[0].length() == 0) {
                throw new Exception("Deadline must have description and time");
            } else {
                throw new Exception("Deadline must have time");
            }
        }

        if (deadlineInputs.length != 2) {
            throw new Exception("Deadline must have description and time");
        }

        String description = deadlineInputs[0];
        LocalDateTime time;
        try {
            time = LocalDateTime.parse(deadlineInputs[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            throw new Exception(
                    "Invalid datetime: " + deadlineInputs[1] + "\n" + "Please use format: YYYY-MM-DD HH:MM:SS");
        }

        return new Deadline(description, time);
    }

    @Override
    public String toString() {
        String timeStr = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss"));

        return "[D]" + super.toString() + " (by: " + timeStr + ")";
    }
}
