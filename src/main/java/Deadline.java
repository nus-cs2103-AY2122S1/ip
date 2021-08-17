/**
 * A subclass of Task of deadline type.
 */
public class Deadline extends Task {
    private String timeInfo;

    Deadline(String name, String deadline) {
        super(name);
        this.timeInfo = deadline;
    }

    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (by: " + timeInfo + ")";

    }

    @Override
    public String printType() {
        return "[D]";
    }
}
