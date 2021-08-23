import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * This class implements a DukeList to be used in storing string from Duke
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Deadline extends Task{
    /** The date and time of the deadline task */
    protected LocalDateTime by;

    /** The DateTimeFormatter used when printing the Event. */
    private static final DateTimeFormatter PRINT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /** The DateTimeFormatter when converting time from string. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy ha");

    /** The DateTimeFormatter when converting time with minutes from string. */
    private static final DateTimeFormatter DATE_MINUTES_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy h:mma");


    /**
     * Constructor for Deadline task
     * @param name the name of the deadline task
     * @param by the deadline of the task
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Converts the user input string to a LocalDateTime.
     * @param by The user input date as a string.
     * @return The LocalDateTime representation.
     * @throws DuchessException Exception thrown when an incorrect format is used for Deadline.
     */
    public static LocalDateTime convertStringToDate (String by) throws DuchessException {
        try {
            String replacement = by.replace("am", "AM").replace("pm","PM");
            System.out.println(replacement);
            return LocalDateTime.parse(replacement,
                    replacement.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new DuchessException("Wrong format used.");
        }
    }

    public LocalDateTime getDateTime(){
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + PRINT_DATE_FORMATTER.format(by)+ ")";
    }
}
