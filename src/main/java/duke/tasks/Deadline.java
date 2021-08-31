package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidTimeStampException;




/** Class representing a deadline */
public class Deadline extends Task {

    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy' 'HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    private LocalDateTime timeInfo;

    /**
     * Deadline constructor.
     *
     * @param taskDetails Details about the task.
     * @param timeInfoString Deadline of the task, to be given in d/M/YYYY HHmm format.
     * @throws InvalidTimeStampException If given timeInfoString is invalid.
     */
    public Deadline(String taskDetails, String timeInfoString) throws InvalidTimeStampException {
        super(taskDetails);
        try {
            this.timeInfo = LocalDateTime.parse(timeInfoString, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeStampException(timeInfoString);
        }
    }

    @Override
    public String toString() {
        String timeInfoString = timeInfo.format(outputFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), timeInfoString);
    }

    @Override
    public String toDataString() {
        String timeInfoString = timeInfo.format(inputFormatter);
        return String.format("D | %s | %s", super.toDataString(), timeInfoString);
    }
}
