package duke.tasks;

import duke.exceptions.InvalidTimeStampException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy' 'HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    private LocalDateTime timeInfo;
    public Event(String taskDetails, String timeInfoString) throws InvalidTimeStampException {
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
        return String.format("[E]%s (at: %s)", super.toString(), timeInfoString);
    }

    @Override
    public String toDataString() {
        String timeInfoString = timeInfo.format(inputFormatter);
        return String.format("E | %s | %s", super.toDataString(), timeInfoString);
    }
}