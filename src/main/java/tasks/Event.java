package tasks;

public class Event extends Task {
    private String taskName;
    private String time;
    private String taskSymbol = "E";

    public Event(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s (at: %s)", taskSymbol, statusIcon, taskName, date);
    }
}
