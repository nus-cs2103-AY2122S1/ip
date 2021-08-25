package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        try {
            int[] dateArgs = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
            this.date = LocalDate.of(dateArgs[0], dateArgs[1], dateArgs[2]);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        String deadlineMarker = "[D]";

        if (isDone) {
            return deadlineMarker + "|" + hasCross + "|" + item + "|" + date.toString();
        } else {
            return deadlineMarker + "|" + hasNoCross + "|" + item + "|" + date.toString();
        }
    }
}
