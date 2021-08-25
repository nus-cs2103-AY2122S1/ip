package duchess.task;

import duchess.main.DuchessException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private static final DateTimeFormatter PRINT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /** The DateTimeFormatter when converting time from string. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy ha");

    /** The DateTimeFormatter when converting time with minutes from string. */
    private static final DateTimeFormatter DATE_MINUTES_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy h:mma");


    /**
     * Constructs a Deadline.
     * @param name The name of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a simplified representation of the Deadline for easier recovery from save file.
     * @return The file formatted string representation of the Deadline.
     */
    public String toFileFormat() {
        return String.format("D%s,%s,%b", name, PRINT_DATE_FORMATTER.format(by), isDone);
    }

    /**
     * Converts the user input string to a LocalDateTime.
     * @param by The user input date as a string.
     * @return The LocalDateTime representation.
     * @throws DuchessException When an incorrect format is used for Deadline.
     */
    public static LocalDateTime convertStringToDate (String by) throws DuchessException {
        try {
            String replacement = by.replace("am", "AM").replace("pm","PM");
            System.out.println(replacement);
            return LocalDateTime.parse(replacement,
                    replacement.contains(":") ? DATE_MINUTES_FORMATTER : DATE_FORMATTER);
        } catch (DateTimeException e) {
            throw new DuchessException("Wrong format used.");
        }
    }

    /**
     * Converts the user input string to a LocalDateTime.
     * @param by The date as text within the file.
     * @return The LocalDateTime representation.
     * @throws DuchessException Exception thrown when an incorrect format is used for Deadline.
     */
    public static LocalDateTime convertTextToDate (String by)  {
        return LocalDateTime.parse(by, PRINT_DATE_FORMATTER);
    }

    public LocalDateTime getDateTime(){
        return this.by;
    }


    /**
     * Returns a string representation of the Deadline, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the Deadline.
    */
     @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + PRINT_DATE_FORMATTER.format(by)+ ")";
    }
}
