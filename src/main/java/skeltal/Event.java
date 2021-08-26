package skeltal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Event extends Task {
    private String time;

    public Event(String rawTime) throws SkeltalException {
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        String time;

        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an event cannot be empty!");
        }

        try {
            LocalDate date = LocalDate.parse(procTime[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            time = date.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        } catch (DateTimeParseException e) {
            time = procTime[1];
        }
        this.time = time;
    }

    private String formatTime() {
        return "(" + this.time + ")";
    }

    @Override
    public String store() {
        return "E | " + super.store() + "| " + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + formatTime();
    }
}
