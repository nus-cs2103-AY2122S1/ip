/**
 * @author Hang Zelin
 * @description ToDos class that extends Task class. It is one of the types in 3 tasks.
 */
package duke.task;

public class ToDos extends Task {

    private boolean done;
    private String task;
    private String taskType = "T";

    /**
     * Constructor for ToDos containing boolean value if the task is done, the
     * specific task info.
     *
     * @param done
     * @param task
     */
    public ToDos(boolean done, String task) {
        this.done = done;
        this.task = task;
    }

    /**
     * Returns the task info in the format of "[type][] task info (/by ...)"
     *
     * @return Task info parsed in the format duke executes.
     */
    @Override
    public String getTaskInfo() {
        String doneStr = "";
        if (!this.done) {
            doneStr = " ";
        } else {
            doneStr = "X";
        }

        return "[" + taskType + "]" + "[" + doneStr + "] " + task;
    }

    /**
     * Returns the Parsed time info in the format of "MMM dd yyyy HH:mm"
     * Noted: This method is only applicable for "event" and "deadline" type task.
     *
     * @return Return the parsed time in the format duke can understand.
     */
    @Override
    public String parsedTime() {
        return null;
    }

    /**
     * Returns the Parsed time info in the format of "dd/mm/yy hhmm".
     * Noted: This method is only applicable for "event" and "deadline" type task,
     *
     * @return Time in the format of "dd/mm/yy hhmm" which duke can understand.
     */
    @Override
    public String getTime() {
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
        return this.taskType + " | " + (this.done ? 1 : 0) + " | " + task;
    }

    /**
     * Mark this task as done.
     */
    @Override
    public void markDone() {
        this.done = true;
    }
}
