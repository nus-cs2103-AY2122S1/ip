/**
 * @author Hang Zelin
 *
 * An abstract class that encapsulates the info basic for a task
 */
package duke.task;

public abstract class Task {

    /**
     * Returns the task info in the format of "[type][] task info (/by ...)"
     *
     * @return Task info parsed in the format duke executes.
     */
    public abstract String getTaskInfo();

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm"
     * Noted: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format duke can understand.
     */
    public abstract String parsedTime();

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Noted: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which duke can understand.
     */
    public abstract String getTime();

    /**
     * Returns the task info in the format of save data requirement, that is: "taskType | done or not | task info | time".
     *
     * @return Task info in the format of "taskType | done or not | task info | time".
     */
    public abstract String getSaveDataInfo();

    /**
     * Mark this task as done.
     */
    public abstract void markDone();
}
