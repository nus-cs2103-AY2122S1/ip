package hyddd.task;

/**
 * @@author Hang Zelin
 *
 * ToDos class that extends Task class. It is one of the types in 3 tasks.
 */
public class ToDo extends Task {
    private static final String TASKTYPE = "T";
    private boolean isDone;
    private final String task;

    /**
     * Constructor for ToDos containing boolean value if the task is done, the
     * specific task info.
     *
     * @param isDone Indicates if the task is done or not.
     * @param task Specifc task info.
     */
    public ToDo(boolean isDone, String task) {
        this.isDone = isDone;
        this.task = task;
    }

    /**
     * Returns the task info in the format of "[type][] task info".
     *
     * @return Task info parsed in the format hyddd executes.
     */
    @Override
    public String getTaskStatus() {
        String doneStatus;
        String taskStatus;
        if (!this.isDone) {
            doneStatus = " ";
        } else {
            doneStatus = "X";
        }

        taskStatus = "[" + TASKTYPE + "]" + "[" + doneStatus + "] " + task;
        return taskStatus;
    }

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm".
     * Note: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format hyddd can understand.
     */
    @Override
    public String parsedTime() {
        return null;
    }

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Note: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which hyddd can understand.
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
        String dataInfo;
        int value;
        if (this.isDone) {
            value = 1;
        } else {
            value = 0;
        }
        dataInfo = TASKTYPE + " | " + value + " | " + task;
        return dataInfo;
    }

    /**
     * Returns if other task has same info as this task.
     *
     * @return A boolean value of whether two task info is the same.
     */
    @Override
    public boolean returnIsSameTask(String task) {
        boolean isSameTask;
        isSameTask = task.equals(this.task);
        return isSameTask;
    }

    /**
     * Marks this task as done.
     */
    @Override
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Resets this task as undone.
     */
    @Override
    public void resetDone() {
        this.isDone = false;
    }
}
