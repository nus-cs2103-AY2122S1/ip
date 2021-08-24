package duke.task;

public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    public Todo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[T]" + "[" + finished + "] " + this.getTaskName();
    }

    @Override
    public String toStoredString() {
        int finished = isDone() ? 1 : 0;
        return "T | " + finished + " | " + this.getTaskName();
    }
}
