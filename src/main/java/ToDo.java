/**
 * A toDo-type of Task that contains details toDo.
 */
public class ToDo extends Task {

    /**
     * Constructs a uncompleted task with the details as taskDetails
     *
     * @param taskDetails task name
     */
    public ToDo(String taskDetails) {
        super(taskDetails);
    }

    /**
     * Returns the task details with the prefix first with [T] followed by the status of completion of the task.
     *
     * @return the task details with the prefix first with [T] followed by the status of completion of the task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

}
