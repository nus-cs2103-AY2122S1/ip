public class ToDo extends Task{
    public ToDo (String taskDetails) {
        super(taskDetails);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
