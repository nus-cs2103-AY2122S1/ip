import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        if(by.contains("/")) {
            date = LocalDate.parse(by.split("/")[2] + "-" + by.split("/")[1] + "-" + (Integer.parseInt(by.split("/")[0]) < 10 ? "0" + by.split("/")[0] : by.split("/")[0]));
        } else {
            date = LocalDate.parse(by);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
