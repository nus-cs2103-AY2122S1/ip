package duke;

/**
 *  This class encapsulates a Task element of Event type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */
public class EventTask extends BaseTask {

    private String atContents;

    /**
     * Constructs a new Event Task.
     *
     * @param taskName the name or description of the task.
     * @param atContents describes where the task should be completed at.
     */
    public EventTask(String taskName, String atContents) {
        super(taskName, false);
        this.atContents = atContents;
    }

    /**
     * Creates a new Event Task with ability to set all attributes.
     *
     * @param taskName the name or description of the task.
     * @param atContents describes where the task should be completed at.
     * @param isCompleted whether the task is already completed.
     */
    public EventTask(String taskName, String atContents, boolean isCompleted) {
        super(taskName, isCompleted);
        this.atContents = atContents;
    }

    /**
     * Gets the type of the Task in enum form.
     *
     * @return the enum representing the type of the current task.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Returns a string containing the letter representing the task type in square brackets.
     *
     * @return a string containing [X] where X is the letter representing the task type.
     */
    @Override
    public String getTaskTypeStringHeader() {
        return "[E]";
    }

    /**
     * Returns a string containing the task's name and additional descriptions if applicable.
     *
     * @return the string describing the task.
     */
    @Override
    public String getTaskDescription() {
        return this.getTaskName() + "(at: " + this.atContents + ")";
    }


    /**
     * Returns the extra info this task contains, which is the at where data.
     *
     * @return the String containing the extra info of this task if any. Returns null if there is none.
     */
    @Override
    public String getTaskExtraInfo() {
        return this.atContents;
    }
}
