package tasks;

public class Deadline extends Task {
    private final String by;

    public Deadline(String deadlineDataText) {
        String[] deadlineData = deadlineDataText.split("/by ", 2);
        super.setDescription(deadlineData[0].trim());
        this.by = deadlineData[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
