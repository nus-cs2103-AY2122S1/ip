import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Event extends Task{
    private LocalDate date;

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Event(String name, String isDone, String eventDate) {
        super(name, isDone.equals("1"));
        this.date = LocalDate.parse(eventDate);
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

    @Override
    public String toSaveString() {
        return "Event~" + super.toSaveString() + "~" + this.date;
    }
}