public class ToDo extends Task {

    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toString() {
        if (super.isDone) {
            return "[T][X] " + super.taskName;
        } else {
            return "[T][ ] " + super.taskName;
        }
    }
}
