package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo other = (Todo) obj;
            return (this.isDone == other.getIsDone() &&
                    this.description.equals(other.getDescription()));
        }
        return false;
    }
}
