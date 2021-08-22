/**
 * A subclass of Task of deadline type.
 */
public class Deadline extends Task {
    private String timeInfo;

    Deadline(String name, String deadline) {
        super(name);
        this.timeInfo = deadline;
    }

    Deadline(String name, String deadline, boolean isDone) {
        super(name, isDone);
        this.timeInfo = deadline;
    }

    @Override
    public String showStatus() {
        String status =  super.showStatus();
        return status + " (by: " + timeInfo + ")";

    }

    @Override
    public String showStatusWrite() {
        return this.printType() + this.printCompletionStatus()
                + Task.separator + this.name
                + Task.separator + this.timeInfo;
    }

    @Override
    public String printType() {
        return "[D]";
    }
}
