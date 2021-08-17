/**
 *  This class encapsulates a Task element of Event type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */

public class EventTask extends TDLTask {

    String atContents;

    public EventTask(String taskName, String atContents) {
        super(taskName);
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

}
