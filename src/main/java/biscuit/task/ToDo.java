package biscuit.task;

/**
 * biscuit.tasks.ToDo class: For tasks without any date/time attached to it
 * e.g., visit new theme park
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
