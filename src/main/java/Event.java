import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

    public static Event createEvent(String desc)
            throws InputMismatchException, DateTimeParseException {
        if (desc.contains("/at")) {
            String[] arr = desc.split("/at");
            LocalDate time = LocalDate.parse(arr[1].trim());
            return new Event(arr[0].trim(), time);
        } else {
            throw new InputMismatchException();
        }
    }

    private Event(String details, LocalDate time) {
        super(details);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
