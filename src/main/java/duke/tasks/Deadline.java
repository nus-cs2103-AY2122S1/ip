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

    /**
     * Returns a String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        String timeInfoString = timeInfo.format(outputFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), timeInfoString);
    }

    /**
     * Returns a String which is used to save data to disk.
     *
     * @return String representation of how data will be saved to disk.
     */
    @Override
    public String toDataString() {
        String timeInfoString = timeInfo.format(inputFormatter);
        return String.format("D | %s | %s", super.toDataString(), timeInfoString);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj Object to compare to
     * @return boolean indicating whehter the other object is "equal" to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline tmp = (Deadline) obj;
            return super.equals(obj) && timeInfo.equals(tmp.timeInfo);
        } else {
            return false;
        }
    }
}
