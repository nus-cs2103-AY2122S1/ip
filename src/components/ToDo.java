package components;

public class ToDo extends Task{

    public ToDo(String taskDescription, boolean done) {
        super(taskDescription, done);
    }

    @Override
    public ToDo markDone() {
        return new ToDo(taskDescription, true);
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
