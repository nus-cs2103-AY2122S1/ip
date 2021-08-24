package duke.tasks;

public class Deadline extends Task {

    public Deadline(String description, String dateTime) {
        super(description, dateTime);
    }

    public Deadline(String description, String dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(this.getDescription(), this.getDateTime(), true);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.getDateTime() + ")";
    }
}
