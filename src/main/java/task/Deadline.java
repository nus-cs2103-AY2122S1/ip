package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String taskName;
    private String time;
    private String taskSymbol = "D";

    public Deadline(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    public String timeToString() {
        String regex = " ";
        String[] splittedTime = time.split(regex);
        // Loop through splittedTime and replace dates with the appropriate format
        for (int i = 0; i < splittedTime.length; i++) {
            try {
                LocalDate date = LocalDate.parse(splittedTime[i]);
                splittedTime[i] = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                // splittedTime[i] is not in the date format, so do nothing
            }
        }
        return String.join(" ", splittedTime);
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s (by: %s)", taskSymbol, statusIcon, taskName, timeToString());
    }

    @Override
    public String toStorageFormat() {
        return String.format("%s/%s/%s/%s", taskSymbol, isCompleted(), taskName, time);
    }

}
