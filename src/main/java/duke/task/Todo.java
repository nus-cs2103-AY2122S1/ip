package duke.task;

/**
 * The To do is a task that only has a description.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Todo extends Task {
    public Todo(String description, String date, String time) {
        super(description, date, time);
    }

    @Override
    public String getReadableString() {
        String binaryStatus = this.isDone ? "1" : "0";
        return "T | " + binaryStatus + " | " + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
