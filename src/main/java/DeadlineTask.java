/**
 *  This class encapsulates a Task element of Deadline type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */

public class DeadlineTask extends TDLTask {

    String byWhen;

    public DeadlineTask(String taskName, String byWhen) {
        super(taskName);
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
}
