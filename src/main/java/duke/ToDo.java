package duke;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description of ToDo cannot be empty!");
        }
        this.type = 'T';
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }

    @Override
    public int compareTo(Task other) {
        return -1;
    }
}
