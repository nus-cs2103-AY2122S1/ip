package components;

public class ToDo extends Task{

    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[T] [X] %s", this.taskDescription);
        } else {
            return String.format("[T] [ ] %s", this.taskDescription);
        }
    }
}
