public class ToDo extends Task{
    public ToDo (String taskDetails) {
        super(taskDetails);
    }

    public String taskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
