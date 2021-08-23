public class Todo extends Task {

    public Todo (String task) {
        super(task);
    }

    public Todo (String task, boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.done) {
            finished = "X";
        }
        return "[T]" + "[" + finished + "] " + this.taskName;
    }

    @Override
    public String toStoredString() {
        int finished = done ? 1 : 0;
        return "T | " + finished + " | " + taskName;
    }
}
