package duke.tasks;

public class Events extends Task {

    public Events(String description, String dateTime) {
        super(description, dateTime);
    }

    public Events(String description, String dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public Events markAsDone() {
        return new Events(this.getDescription(), this.getDateTime(), true);
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.getDateTime() + ")";
    }
}
