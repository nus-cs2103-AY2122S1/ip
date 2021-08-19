public class ToDo extends Task {
    public ToDo(String taskname) {
        super(taskname);
    }

    @Override
    public String toString() {
        return "ToDo: " + super.toString();
    }
}
