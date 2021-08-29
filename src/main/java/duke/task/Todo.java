package duke.task;

/**
 * This class encapsulates a Todo.
 *
 * @author Teo Sin Yee
 * @version CS2103T AY21/22 Semester 1
 */
public class Todo extends Task {

    /**
     * Instantiates a new Todo.
     *
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatToSave() {
        return String.format("T | %s", super.formatToSave());
    }

    /**
     * String representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Todo) {
            return ((Todo) o).getTaskName().equals(this.getTaskName());
        }
        return false;
    }
}
