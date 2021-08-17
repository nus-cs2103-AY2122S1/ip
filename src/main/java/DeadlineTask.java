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
}
