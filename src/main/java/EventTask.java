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

}
