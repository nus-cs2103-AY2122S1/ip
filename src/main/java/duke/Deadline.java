package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, String isDone, String doneBefore) {
        super(name, isDone.equals("1"));
        this.date = LocalDate.parse(doneBefore);
    }

    /**
     * parses a command and generates a new Deadline instance.
     * @param newCommand the full Command.
     * @return the new Deadline instance.
     * @throws IllegalArgumentException If the command is of illegal format.
     * @throws DateTimeParseException If the due date is of illegal format.
     */
    public static Deadline parseNewCommand(String newCommand)
            throws IllegalArgumentException, DateTimeParseException {
        int sepIndex = newCommand.indexOf("/by");
        int cmdLen = newCommand.length();
        if (cmdLen < 9 || 9 > sepIndex - 1 || cmdLen < sepIndex + 4) {
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

    /**
     * Returns the Deadline item as a string for storage.
     * @return the string for storage.
     */
    @Override
    public String toSaveString() {
        return "duke.Deadline~" + super.toSaveString() + "~" + this.date;
    }
}