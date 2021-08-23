import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    String dlineString;
    LocalDate dlineDate;

    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by ") - 1));
        String dline = input.substring(input.indexOf("/by ") + 4);
        try {
            this.dlineDate = LocalDate.parse(dline);
        } catch (DateTimeParseException e) {
            this.dlineString = dline;
        }
    }

    @Override
    public String toString() {
        String msg = "[D]" + super.toString() + " (by: ";
        if (dlineString == null) {
            msg = msg + dlineDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
        } else {
            msg = msg + dlineString + ")";
        }
        return msg;
    }
}
