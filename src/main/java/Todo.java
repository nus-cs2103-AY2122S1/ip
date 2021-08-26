public class Todo extends Task{

    Todo(String taskName, boolean status) {
        super(taskName, status);
    }

    public String displayTask() {
        return "T " + super.displayTask();
    }
}
