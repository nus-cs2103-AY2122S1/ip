import java.util.Scanner;

public class Deadline extends Task {
    public String time;

    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getSymbol() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getSymbol() + " " + super.toString() + "(by:" + this.time + ")";
    }
}
