package tasks;

public class Event extends Task {
    private String taskName;
    private String date;
    private String taskSymbol = "E";

    public Event(String taskName, String date) {
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s (at: %s)", taskSymbol, statusIcon, taskName, date);
    }

    @Override
    public String toStorageFormat() {
        return String.format("%s/%s/%s/%s", taskSymbol, isCompleted(), taskName, date);
    }
}
