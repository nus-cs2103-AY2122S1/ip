package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.data.exception.DukeException;
import duke.data.exception.EmptyTimeException;
import duke.data.exception.InvalidTimeException;

/**
 * Encapsulates the Deadline task
 */
public class Deadline extends Task {
    /** The identifier of this task */
    protected static final String IDENTIFIER = "D";

    /** The deadline of the task */
    protected LocalDateTime by;

    /**
     * Constructor for Deadline
     *
     * @param input the input array consisting of description and date/time
     * @throws EmptyTimeException if time is empty
     * @throws InvalidTimeException if time is not in correct format
     */
    public Deadline(String[] input) throws DukeException {
        super(input[0]);

        boolean isEmptyTimeInput = input.length < 2;
        if (isEmptyTimeInput) {
            throw new EmptyTimeException();
        }

        String timeInput = input[1];
        try {
            this.by = LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern(INPUT_DATE_TIME_FORMAT_PATTERN));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException();
        }
    }

    /**
     * Constructor for Deadline specifying isDone
     *
     * @param input the input array consisting of description and date/time from saved data
     * @param isDone deadline's status from saved data
     */
    public Deadline(String[] input, boolean isDone) {
        super(input[0], isDone);
        assert(!input[1].isEmpty());
        this.by = LocalDateTime.parse(input[1]);
    }

    @Override
    public String convertToData() {
        return String.format("%s/%s/%s/%s", IDENTIFIER, this.isDone ? "1" : "0", this.description, this.by);
    }

    /**
     * Returns string representation of this deadline
     *
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                IDENTIFIER,
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT_PATTERN)));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return d.description.equals(this.description) && d.by.isEqual(this.by);
        }
        return false;
    }
}
