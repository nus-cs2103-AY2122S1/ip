public class ToDo extends Task{
    private String taskType = "T";
    public ToDo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.taskType, super.toString());
    }
}
