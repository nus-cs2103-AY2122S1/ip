package seedu.duke.tasks;

public class TimedTask extends Task {

    public TimedTask(String description, String dateTime) {
        super(description, dateTime);
    }

    public TimedTask(String description, String dateTime, boolean isDone) {
        super(description, dateTime, isDone);
    }

    @Override
    public String getSymbol() {
        return "TT";
    }

    @Override
    public TimedTask markAsDone() {
        return new TimedTask(this.getDescription(), this.getDateTime(), true);
    }

    @Override
    public String toString() {
        return "[TT][" + this.getStatusIcon() + "] " + this.getDescription() + " (needs " + this.getDateTime() + ")";
    }

}
