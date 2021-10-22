package virushade.tasks;

public class ToDo extends Task {
    /**
     * The constructor for a Todos Task.
     * @param taskDescription The name of the task.
     */
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    /**
     * @return String representation of ToDos task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
