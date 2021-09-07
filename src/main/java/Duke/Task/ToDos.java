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
        String dataInfo;
        int value = 0;
        if (this.isDone) {
            value = 1;
        } else {
            value = 0;
        }
        dataInfo =  this.TASKTYPE + " | " + value + " | " + task;
        return dataInfo;
    }

    /**
     * Mark this task as done.
     */
    @Override
    public void markDone() {
        this.isDone = true;
    }
}
