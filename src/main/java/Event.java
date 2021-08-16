import java.util.Scanner;

public class Event extends Task {
    public String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getSymbol() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getSymbol() + " " + super.toString() + "(at:" + this.time + ")";
    }
}
