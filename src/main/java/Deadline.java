import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        int[] deadlineArr = Arrays.stream(by.split("-")).mapToInt(Integer::parseInt).toArray();
        this.by = LocalDate.of(deadlineArr[0], deadlineArr[1], deadlineArr[2]);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }
}
