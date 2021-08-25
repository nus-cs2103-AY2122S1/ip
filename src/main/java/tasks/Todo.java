package tasks;

public class Todo extends Task {

    public Todo(String desc, Boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String saveText() {
        int isDone = this.isDone ? 1 : 0;
        return "T | " + isDone + " | " + desc + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + this.completionStatus() + desc;
    }
}
