import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task{
    private String eventDate;
    private LocalDate date;

    public Event(String name, String eventDate) {
        super(name);
        this.eventDate = eventDate; // stored internally as YYYY-MM-DD
        this.date = LocalDate.parse(eventDate);
    }

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
        this.eventDate = date.toString();
    }

    public static Event parseNewCommand(String newCommand) throws IllegalArgumentException, DateTimeParseException {
        int sepIndex = newCommand.indexOf("/at");
        int cmdLen = newCommand.length();
        if (sepIndex == -1 || cmdLen < 6 || 6 > sepIndex-1 || cmdLen < sepIndex+4) {
            throw new IllegalArgumentException("Invalid command for a new event.");
        }
        String newName = newCommand.substring(6, sepIndex-1);
        String newDate = newCommand.substring(sepIndex+4);
        LocalDate newLocalDate = LocalDate.parse(newDate);

        return new Event(newName, newLocalDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (by: %s)", this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}