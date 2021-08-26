package duke.data.tasks;

public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    public ToDos(boolean completed, String name) {
        super(completed, name);
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return "T~1~" + this.getName();
        } else {
            return "T~0~" + this.getName();
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

        return this.getName().equals(todo.getName()) &&
                this.isCompleted() == todo.isCompleted();
    }
}
