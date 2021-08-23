import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String taskDescription, String dateString) {
        super(taskDescription);
        try {
            int[] dateArgs = Arrays.stream(dateString.split("-")).mapToInt(Integer::valueOf).toArray();
            this.eventTime = LocalDate.of(dateArgs[0], dateArgs[1], dateArgs[2]);
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidDukeCommandException("Event date has to be declared in the format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.eventTime.format(DateTimeFormatter
                .ofPattern("dd MMM yyyy")));
    }
}
