package duke.Tasks;

/**
 *  This class encapsulates a Task element of Deadline type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */

public class DeadlineTask extends BaseTask {

    private String byWhen;

    /**
     * Used for creating a new Deadline Task.
     *
     * @param taskName the name or description of the task.
     * @param byWhen describes when the task should be completed by.
     */
    public DeadlineTask(String taskName, String byWhen) {
        super(taskName, false);
        this.byWhen = byWhen;
    }

    /**
     * Used for creating a new Deadline Task with ability to set all attributes.
     *
     * @param taskName the name or description of the task.
     * @param byWhen describes when the task should be completed by.
     * @param isCompleted true if task was already complete, false if incomplete.
     */
    public DeadlineTask(String taskName, String byWhen, boolean isCompleted) {
        super(taskName, isCompleted);
        this.byWhen = byWhen;
    }


    /**
     * Gets the type of the Task in enum form.
     *
     * @return the enum representing the type of the current task.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string containing the letter representing the task type in square brackets.
     *
     * @return a string containing [X] where X is the letter representing the task type.
     */
    @Override
    public String getTaskTypeStringHeader() {
        return "[D]";
    }

    /**
     * Returns a string containing the task's name and additional descriptions if applicable.
     *
     * @return the string describing the task.
     */
    @Override
    public String getTaskDescription() {
        return this.getTaskName() + "(by: " + this.byWhen + ")";
    }

    /**
     * Returns the extra info this task contains, which is the by when data.
     *
     * @return the String containing the extra info of this task if any. Returns null if there is none.
     */
    @Override
    public String getTaskExtraInfo() {
        return this.byWhen;
    }
}
