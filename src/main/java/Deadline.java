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
        String[] timeInfoList = this.timeInfo.split(" ", 2);
        return status + " (" + timeInfoList[0] + ": " + timeInfoList[1] + ")";
    }

    @Override
    public String printType() {
        return "[D]";
    }
}
