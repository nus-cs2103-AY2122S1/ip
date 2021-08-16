import java.util.Scanner;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getSymbol() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getSymbol() + " " + super.toString();
    }
}
