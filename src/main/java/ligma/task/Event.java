package ligma.task;

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
            throw new InputMismatchException("Time must be stipulated for events using '/at'.");
        }
    }

    public static Event createEvent(String desc, boolean isDone) throws InputMismatchException {
        if (desc.contains("at")) {
            int i = desc.indexOf('(');
            LocalDate time = LocalDate.parse(desc.substring(i + 5, desc.length() - 1),
                    DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Event(desc.substring(0, i - 1),
                    time,
                    isDone);
        } else {
            throw new InputMismatchException();
        }
    }

    private Event(String details, LocalDate time) {
        super(details);
        this.time = time;
    }

    private Event(String details, LocalDate time, boolean isDone) {
        super(details, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
