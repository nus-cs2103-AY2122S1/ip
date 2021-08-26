/**
 * @author Hang Zelin
 * @description Task is an abstract class that encapsulates the info basic for a task
 */
package duke.task;

public abstract class Task {

    /**
     * @param
     * @return String
     * @author Hang Zelin
     * @description Return the task info in the form of "[type][] task info (/by ...)"
     */
    public abstract String getTaskInfo();

    /**
     * @param
     * @return String
     * @author Hang Zelin
     * @description Return the Parsed time info in the form of "MMM dd yyyy HH:mm"
     * noted: This method is only applicable for "event" and "deadline" type task,
     */
    public abstract String ParsedTime();

    /**
     * @param
     * @return String
     * @author Hang Zelin
     * @description Return the Parsed time info in the form of "dd/mm/yy hhmm".
     * noted: This method is only applicable for "event" and "deadline" type task,
     */
    public abstract String getTime();

    /**
     * @param
     * @return String
     * @author Hang Zelin
     * @description Return the task info in the form of save data requirement, that is: "tasktype | done or not | task info | time".
     */
    public abstract String getSaveDataInfo();

    /**
     * @param
     * @return void
     * @author Hang Zelin
     * @description Mark the task as done.
     */
    public abstract void MarkDone();
}
