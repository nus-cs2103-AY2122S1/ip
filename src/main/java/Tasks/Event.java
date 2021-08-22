package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy' 'HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private LocalDateTime timeInfo;
    public Event(String taskDetails, String timeInfoString) {
        super(taskDetails);
        this.timeInfo = LocalDateTime.parse(timeInfoString, inputFormatter);
    }

    @Override
    public String toString() {
        String timeInfoString = timeInfo.format(outputFormatter);
        return String.format("[E]%s (at: %s)", super.toString(), timeInfoString);
    }

    @Override
    public String dataToString() {
        String timeInfoString = timeInfo.format(inputFormatter);
        return String.format("E | %s | %s", super.dataToString(), timeInfoString);
    }
}
