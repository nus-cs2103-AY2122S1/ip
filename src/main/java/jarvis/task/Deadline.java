package jarvis.task;

import jarvis.exception.InvalidDateTimeInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String INPUT_FORMAT =  "dd-MM-yyyy HHmm";
    private static final String OUTPUT_FORMAT = "MMM d yyyy HHmm";
    private LocalDateTime deadline;

    public Deadline(String description, String deadline) throws InvalidDateTimeInputException {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        try {
            this.deadline = LocalDateTime.parse(deadline, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeInputException("deadline", INPUT_FORMAT);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDeadline);
    }

    @Override
    public String toStorageFormatString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        String formattedDeadlineInput = this.deadline.format(dateTimeFormatter);
        return String.format("%s;;;%s;;;%s", "D", super.toStorageFormatString(), formattedDeadlineInput);
    }
}
