import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    LocalDate deadline;

    public Deadline(String name) throws DukeDeadlineException, DateTimeException {
        super(name.substring(0, name.indexOf(" /by ") + 1));
            this.deadline = LocalDate.parse(name.substring(name.indexOf(" /by ") + 5));
        if (name.equals("")) {
            throw  new DukeDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadline + ")";
    }

    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + this.deadline;
    }
}
