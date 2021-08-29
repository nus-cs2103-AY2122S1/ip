/**
 * @author Hang Zelin
 *
 * @description Task is an abstract class that encapsulates the info basic for a task
 *
 */
package Duke.Task;

public abstract class Task {

    /**
     * @author Hang Zelin
     *
     * @description Return the task info in the form of "[type][] task info (/by ...)"
     *
     * @param
     * @return String
     */
    public abstract String getTaskInfo();

    /**
     * @author Hang Zelin
     *
     * @description Return the Parsed time info in the form of "MMM dd yyyy HH:mm"
     * noted: This method is only applicable for "event" and "deadline" type task,
     *
     * @param
     * @return String
     */
    public abstract String ParsedTime();

    /**
     * @author Hang Zelin
     *
     * @description Return the Parsed time info in the form of "dd/mm/yy hhmm".
     * noted: This method is only applicable for "event" and "deadline" type task,
     *
     * @param
     * @return String
     */
    public abstract String getTime();

    /**
     * @author Hang Zelin
     *
     * @description Return the task info in the form of save data requirement, that is: "tasktype | done or not | task info | time".
     *
     * @param
     * @return String
     */
    public abstract String getSaveDataInfo();

    /**
     * @author Hang Zelin
     *
     * @description Mark the task as done.
     *
     * @param
     * @return void
     */
    public abstract void MarkDone();
}
