package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.exception.InvalidDateTimeInputException;

/**
 * Encapsulates the deadline task which contains a description and a deadline.
 */
public class Deadline extends Task {
    /**
     * Input format for the task deadline.
     */
    public static final String INPUT_FORMAT = "dd-MM-yyyy HHmm";
    private static final String OUTPUT_FORMAT = "MMM d yyyy HHmm";
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     *
     * @param description The deadline task description.
     * @param deadline The task deadline.
     * @throws InvalidDateTimeInputException If deadline is not of proper format.
     */
    public Deadline(String description, String deadline) throws InvalidDateTimeInputException {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        try {
            this.deadline = LocalDateTime.parse(deadline, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeInputException("deadline", INPUT_FORMAT);
        }
    }

    /**
     * Gets the string representation of a deadline task.
     *
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDeadline);
    }

    /**
     * Gets the string representation of a deadline task that is to be saved to storage file.
     *
     * @return String representation that is to be saved.
     */
    @Override
    public String toStorageFormatString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        String formattedDeadlineInput = this.deadline.format(dateTimeFormatter);
        return String.format("%s;;;%s;;;%s", "D", super.toStorageFormatString(), formattedDeadlineInput);
    }
}
