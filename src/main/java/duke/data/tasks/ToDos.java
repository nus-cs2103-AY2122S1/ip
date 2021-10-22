package duke.data.tasks;

/**
 * Represents a ToDoTask.
 */
public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    public ToDos(boolean completed, String name, String tags) {
        super(completed, name, tags);
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("T~1~%s~%s", this.getName(), this.getTags());
        } else {
            return String.format("T~0~%s~%s", this.getName(), this.getTags());
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDos)) return false;

        ToDos todo = (ToDos) o;

        return this.getName().equals(todo.getName())
                && this.isCompleted() == todo.isCompleted();
    }
}
