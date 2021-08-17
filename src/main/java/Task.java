import java.util.Objects;

public class Task {
    private final String description, taskType;
    private boolean isDone;

    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    /**
     * Gets the status string <code>[&lt;taskType&gt;][&lt;isDone&gt;]</code>
     *
     * @return the associated string
     */
    public String getStatus() {
        return (isDone ? String.format("[%s][X]", taskType) : String.format("[%s][ ]", taskType)); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatus(), this.getDescription());
    }
}
