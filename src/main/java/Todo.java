public class Todo extends Task{

    Todo(String taskName) {
        super(taskName);
    }

    public String displayTask() {
        return "[T]" + super.displayTask();
    }
}
