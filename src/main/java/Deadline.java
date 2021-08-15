public class Deadline extends Task {
    private String deadlineDetails;
    public Deadline(String taskDetails, String deadlineDetails) {
        super(taskDetails);
        this.deadlineDetails = deadlineDetails;
    }
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadlineDetails);
    }
}
