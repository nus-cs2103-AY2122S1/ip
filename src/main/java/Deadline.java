import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate by;

    public Deadline(String input, String by) throws DateTimeParseException{
        super(input);
        int space = by.indexOf(" ");
        String format = space > 0 ? "d/MM/yyyy HHmm" : "d/MM/yyyy"; 
        this.by = LocalDate.parse(by.trim(), DateTimeFormatter.ofPattern(format));
        if (this.by.isBefore(LocalDate.now())) {
            super.completeItem();
        }
    }

    @Override
    public boolean compareDate(LocalDate compare) {
        return this.by.equals(compare);
    }

    @Override
    public  String toString() {
        return "[D] " + super.toString() + "(by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
