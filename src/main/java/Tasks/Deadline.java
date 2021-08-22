package Tasks;
import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy' 'HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private LocalDateTime timeInfo;
    public Deadline(String taskDetails, String timeInfoString) {
        super(taskDetails);
        this.timeInfo = LocalDateTime.parse(timeInfoString, inputFormatter);
    }

    public String toString() {
        String timeInfoString = timeInfo.format(outputFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), timeInfoString);
    }

    @Override
    public String dataToString() {
        String timeInfoString = timeInfo.format(inputFormatter);
        return String.format("D | %s | %s", super.dataToString(), timeInfoString);
    }
}
