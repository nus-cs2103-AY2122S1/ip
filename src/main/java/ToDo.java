public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
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
