package tasks;

public class Deadline extends Task {
    private String taskName;
    private String date;
    private String taskSymbol = "D";

    public Deadline(String taskName, String date) {
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s (by: %s)", taskSymbol, statusIcon, taskName, date);
    }

}
