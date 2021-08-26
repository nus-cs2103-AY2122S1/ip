import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    LocalDate byDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    String[] monthArray = new  String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = LocalDate.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + monthArray[byDate.getMonthValue()] + " " + byDate.getDayOfMonth() + " " + byDate.getYear() + ")";
    }
}