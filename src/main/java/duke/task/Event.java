package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that handles events which is a type of Task.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructor to initialize event.
     *
     * @param detail Description of event.
     * @param at Timing of event in LocalDateTime format.
     */
    public Event(String detail, LocalDateTime at) {
        super(detail, "[E]");
        this.at = at;
    }

    /**
     * Returns time of event in LocalDateTime format.
     *
     * @return Timing of event.
     */
    public LocalDateTime getTime() {
        return at;
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
        String time = at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return super.toString() + " (at: " + time + ")";
    }
}
