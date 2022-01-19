package duchess.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duchess.main.DuchessException;

/**
 * This class implements a Deadline task.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task {
    /** The DateTimeFormatter used when printing the Event.*/
    private static final DateTimeFormatter PRINT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /** The date and time of the deadline task.*/
    protected LocalDateTime dueDate;

    /**
     * Constructs a Deadline.
     * @param name The name of the deadline task.
     * @param dueDate The deadline of the task.
     */
    public Deadline(String name, LocalDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Returns a simplified representation of the Deadline for easier recovery from save file.
     * @return The file formatted string representation of the Deadline.
     */
    public String toFileFormat() {
        return String.format("D%s,%s,%b", name, PRINT_DATE_FORMATTER.format(dueDate), isDone);
    }

    /**
     * Converts the user input string to a LocalDateTime.
     * @param by The date as text within the file.
     * @return The LocalDateTime representation.
     * @throws DuchessException Exception thrown when an incorrect format is used for Deadline.
     */
    public static LocalDateTime convertTextToDate (String by) {
        return LocalDateTime.parse(by, PRINT_DATE_FORMATTER);
    }

    public LocalDateTime getDateTimeStart() {
        return this.dueDate;
    }


    /**
     * Returns a string representation of the Deadline, with an [X] marked for done and [ ] as undone.
     * @return the string representation of the Deadline.
    */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + PRINT_DATE_FORMATTER.format(dueDate) + ")";
    }

    /**
     * Compares the Deadline with another object.
     * @param o The object to compare with.
     * @return Whether the object is a Deadline with the same description and date.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deadline)) {
            return false;
        } else {
            Deadline d = (Deadline) o;
            return d.name.equals(this.name) && d.dueDate.equals(this.dueDate);
        }
    }
}
