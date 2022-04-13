package duke;

/**
 *  This class encapsulates a Task element of ToDo type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */
public class ToDosTask extends BaseTask {

    /**
     * Creates a new To Do Task.
     *
     * @param taskName the name or description of the task.
     */
    public ToDosTask(String taskName) {
        super(taskName, false);
    }


    /**
     * Creates and directly sets all attributes of a To Do Task, for example when loading it from a Save File.
     *
     * @param taskName the name or description of the task.
     * @param isCompleted whether the task is already completed.
     */
    public ToDosTask(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);

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



