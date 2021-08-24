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
            throw new InputMismatchException();
        }
    }

    private Deadline(String details, LocalDate time) {
        super(details);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
