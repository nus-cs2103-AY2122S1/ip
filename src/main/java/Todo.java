import java.util.Scanner;

public class Todo extends Task {
    public String taskName = "";
    private boolean state = false;

    public Todo(String input) {
        Scanner line = new Scanner(input);
        line.next();
        while (line.hasNext()) {
            taskName = taskName + line.next() + " ";
        }
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getSymbol() {
        return "[T]";
    }

    @Override
    public String toString() {
        return taskName;
    }
}
