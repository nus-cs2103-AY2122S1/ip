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

    /**
     * Returns a string containing the letter representing the task type in square brackets.
     *
     * @return a string containing [X] where X is the letter representing the task type.
     */
    @Override
    public String getTaskTypeStringHeader() {
        return "[T]";
    }

}



