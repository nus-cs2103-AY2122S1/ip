package hyddd.task;

/**
 * @@author Hang Zelin
 *
 * An abstract class that encapsulates the info basic for a task.
 */
public abstract class Task {
    /**
     * Returns the task info in the format of "[type][] task info ..."
     *
     * @return Task info parsed in the format hyddd executes.
     */
    public abstract String getTaskStatus();

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm".
     * Noted: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format hyddd can understand.
     */
    public abstract String parsedTime();

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Noted: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which hyddd can understand.
     */
    public abstract String getTimeForSaveData();

    /**
     * Returns the task info in the format of save data requirement,
     * that is: "taskType | done or not | task info | time".
     *
     * @return Task info in the format of "taskType | done or not | task info | time".
     */
    public abstract String getSaveDataInfo();

    /**
     * Returns if other task has same info as this task.
     *
     * @return A boolean value of whether two task info is the same.
     */
    public abstract boolean returnIsSameTask(String task);

    /**
     * Marks this task as done.
     */
    public abstract void markDone();

    /**
     * Resets this task as undone.
     */
    public abstract void resetDone();
}
