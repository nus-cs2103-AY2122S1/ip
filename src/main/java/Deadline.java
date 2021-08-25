import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    public static Deadline createDeadline(String desc)
            throws InputMismatchException, DateTimeParseException {
        if (desc.contains("/by")) {
            String[] arr = desc.split("/by");
            LocalDate time = LocalDate.parse(arr[1].trim());
            return new Deadline(arr[0].trim(), time);
        } else {
            throw new InputMismatchException("Time must be stipulated for deadlines using '/by'.");
        }
    }

    public static Deadline createDeadline(String desc, boolean isDone) throws InputMismatchException {
        if (desc.contains("by")) {
            int i = desc.indexOf('(');
            LocalDate time = LocalDate.parse(desc.substring(i + 5, desc.length() - 1),
                    DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new Deadline(desc.substring(0, i - 1),
                    time,
                    isDone);
        } else {
            throw new InputMismatchException("");
        }
    }

    private Deadline(String details, LocalDate time) {
        super(details);
        this.time = time;
    }

    private Deadline(String details, LocalDate time, boolean isDone) {
        super(details, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
