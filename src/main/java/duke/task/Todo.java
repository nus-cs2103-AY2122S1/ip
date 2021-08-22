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

            // Check is done status and description are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);

            return (isDoneStatusSame && isDescriptionSame);
        }
        return false;
    }
}
