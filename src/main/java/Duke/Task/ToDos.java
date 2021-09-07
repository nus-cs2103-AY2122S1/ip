package duke.task;

/**
 * @@author Hang Zelin
 *
 * ToDos class that extends Task class. It is one of the types in 3 tasks.
 */
public class ToDos extends Task {

    private boolean isDone;
    private String task;
    private static final String TASKTYPE = "T";

    /**
     * Constructor for ToDos containing boolean value if the task is done, the
     * specific task info.
     *
     * @param isDone
     * @param task
     */
    public ToDos(boolean isDone, String task) {
        this.isDone = isDone;
        this.task = task;
    }

    /**
     * Returns the task info in the format of "[type][] task info (/by ...)"
     *
     * @return Task info parsed in the format duke executes.
     */
    @Override
    public String getTaskStatus() {
        String doneStatus;
        if (!this.isDone) {
            doneStatus = " ";
        } else {
            doneStatus = "X";
        }

        return "[" + TASKTYPE + "]" + "[" + doneStatus + "] " + task;
    }

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm"
     * Note: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format duke can understand.
     */
    @Override
    public String parsedTime() {
        return null;
    }

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Note: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which duke can understand.
     */
    @Override
    public String getTimeForSaveData() {
        return null;
    }

    /**
     * Returns the task info in the format of save data requirement,
     * that is: "taskType | done or not | task info | time".
     *
     * @return Task info in the format of "taskType | done or not | task info | time".
     */
    @Override
    public String getSaveDataInfo() {
        return this.TASKTYPE + " | " + (this.isDone ? 1 : 0) + " | " + task;
    }

    /**
     * Mark this task as done.
     */
    @Override
    public void markDone() {
        this.isDone = true;
    }
}
