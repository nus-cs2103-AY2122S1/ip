package Tasks;

import java.io.File;

public class Event extends Task {
    private String timeInfo;
    public Event(String taskDetails, String timeInfo) {
        super(taskDetails);
        this.timeInfo = timeInfo;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeInfo);
    }

    @Override
    public String dataToString() {
        return String.format("E | %s | %s", super.dataToString(), this.timeInfo);
    }
}
