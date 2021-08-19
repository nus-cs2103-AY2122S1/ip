public class ToDo extends Task {
    public ToDo(TaskType taskType, String taskDescription) {
        super(taskType, taskDescription);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TODO.getAbbr() + "]" + super.toString();
    }
}
