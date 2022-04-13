package duke.task;

public class ToDo extends Task {

    /**
     * constructor for ToDo class
     *
     * @param taskName name of task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the Todo
     *
     * @return string representation of the Todo
     */
    @Override
    public String toString() {
        return "[T]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName();
    }
}
