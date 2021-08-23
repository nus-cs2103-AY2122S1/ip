package duke;

import duke.exception.InvalidDukeCommandException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Task {
    protected LocalDate finishDate;

    public Deadline(String taskDescription, String dateString) throws InvalidDukeCommandException {
        super(taskDescription);
        try {
            int[] dateArgs = Arrays.stream(dateString.split("-")).mapToInt(Integer::valueOf).toArray();
            this.finishDate = LocalDate.of(dateArgs[0], dateArgs[1], dateArgs[2]);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException("duke.Deadline date has to be declared in the format yyyy-mm-dd.");
        }
    }

    @Override
    public String toDukeStoreFormat() {
        return String.format("D | %s | %s", super.toDukeStoreFormat(), finishDate);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.finishDate.format(DateTimeFormatter
                .ofPattern("dd MMM yyyy")));
    }
}
