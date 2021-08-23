import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    String timeString;
    LocalDate timeDate;

    public Event(String input) {
        super(input.substring(0, input.indexOf("/at ") - 1));
        String time = input.substring(input.indexOf("/at ") + 4);
        try {
            this.timeDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            this.timeString = time;
        }
    }

    @Override
    public String toString() {
        String msg = "[E]" + super.toString() + " (at: ";
        if (timeString == null) {
            msg = msg + timeDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
        } else {
            msg = msg + timeString + ")";
        }
        return msg;
    }
}
