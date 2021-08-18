public class Todo extends Task {
    public Todo (String task) {
        super(task);
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.done) {
            finished = "X";
        }
        return "[T]" + "[" + finished + "] " + this.taskName;
    }
}
