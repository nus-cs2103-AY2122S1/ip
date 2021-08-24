import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class Deadline extends Task {

    private LocalDate date;

    public Deadline(String description, String date) {
        super(description);
        try {
            int[] dateArr = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
            this.date = LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("the event date has to be in format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        String eventMarker = "[D]";

        String timestamp = String.format("(by: %s)", date.toString());

        if (isDone) {
            return eventMarker + hasCross + " " + item + " " + timestamp;
        } else {
            return eventMarker + hasNoCross + " " + item + " " + timestamp;
        }
    }
}
