public class ToDo extends Task{
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + checkIfTaskCompleted(this) + "] " + this.getTaskName();
    }
}
