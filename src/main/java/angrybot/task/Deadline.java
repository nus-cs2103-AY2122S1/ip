package angrybot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the representation of a task with deadline.
 * The deadline task has a description of what is due by a certain datetime.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class Deadline extends TaskWithDateTime {

    /**
     * Constructor of a Deadline task.
     *
     * @param description The description of the task to be done
     * @param isDone      Boolean representing if task is done.
     * @param by          The deadline for the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone, 'D', by);
    }

    /**
     * Returns a string containing the type of task,
     * whether the task is done, its description, and its deadline.
     *
     * @return The string containing the information of the task.
     */
    @Override
    public String checkStatus() {
        return super.checkStatus() + " " + showDeadline();
    }

    /**
     * Returns a string containing the dateline of the task,
     * in the format of dd MMM yyyy HHmm.
     *
     * @return The string containing the deadline.
     */
    public String showDeadline() {
        return String.format("(by: %s)", dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }

    /**
     * Returns a string representation of the deadline task.
     * Used in storing of data to local directory.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + String.format("|%s", dateTime);
    }
}
