import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, String isDone, String doneBefore) {
        super(name, isDone.equals("1"));
        this.date = LocalDate.parse(doneBefore);
    }

    public static Deadline parseNewCommand(String newCommand) throws IllegalArgumentException, DateTimeParseException {
        int sepIndex = newCommand.indexOf("/by");
        int cmdLen = newCommand.length();
        if (sepIndex == -1 || cmdLen < 9 || 9 > sepIndex-1 || cmdLen < sepIndex+4) {
            throw new IllegalArgumentException("Invalid command for a new deadline.");
        }

        String newName = newCommand.substring(9, sepIndex-1);
        String newDate = newCommand.substring(sepIndex+4);
        LocalDate newLocalDate = LocalDate.parse(newDate);

        return new Deadline(newName, newLocalDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toSaveString() {
        return "Deadline~" + super.toSaveString() + "~" + this.date;
    }
}