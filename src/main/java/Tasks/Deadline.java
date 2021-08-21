package Tasks;

public class Deadline extends Task {
    private String timeInfo;
    public Deadline(String taskDetails, String timeInfo) {
        super(taskDetails);
        this.timeInfo = timeInfo;
    }
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.timeInfo);
    }
}
