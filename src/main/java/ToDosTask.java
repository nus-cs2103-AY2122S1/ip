/**
 *  This class encapsulates a Task element of ToDo type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */

public class ToDosTask extends TDLTask {

    public ToDosTask(String taskName) {
        super(taskName);
    }


    /**
     * Gets the type of the Task in enum form.
     *
     * @return the enum representing the type of the current task.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

}



