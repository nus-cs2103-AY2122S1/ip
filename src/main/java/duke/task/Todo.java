package duke.task;

public class Todo extends Task {
    public Todo(String description, String date, String time) {
        super(description, date, time);
    }

    @Override
    public String getReadableString() {
        String status = this.isDone ? "1" : "0";
        return "T | " + status + " | " + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
