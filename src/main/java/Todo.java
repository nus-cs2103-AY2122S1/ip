public class Todo extends Task {
    public Todo (String task) {
        super(task);
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[T]" + "[" + finished + "] " + this.getTaskName();
    }
}
