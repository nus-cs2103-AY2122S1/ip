package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that handles deadline which is a type of Task.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor to initialize Deadlines.
     *
     * @param detail Description of deadline.
     * @param by Time that the deadline is due by in LocalDateTime format.
     */
    public Deadline(String detail, LocalDateTime by) {
        super(detail, "[D]");
        this.by = by;
    }

    /**
     * Returns time of deadline in LocalDateTime format.
     *
     * @return Timing of deadline.
     */
    public LocalDateTime getTime() {
        return by;
    }


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String ddl = by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return super.toString() + " (by: " + ddl + ")";
    }
}
