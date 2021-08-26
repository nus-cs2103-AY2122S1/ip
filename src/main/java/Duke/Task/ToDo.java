package Duke.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskType = TaskType.T;
    }
    @Override
    public boolean equals(Object object) {
        ToDo otherTodo = (ToDo) object;
        return this.getStatusIcon().equals(otherTodo.getStatusIcon()) &&
                this.description.equals(otherTodo.description) &&
                this.taskType.equals(otherTodo.taskType);
    }
}
