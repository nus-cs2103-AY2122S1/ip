import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String type;
    private LocalDate deadline;

    Deadline(String title, String deadline) throws InvalidDeadlineException {
        super(title);
        this.type = "D";
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + "(by:" +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    String writeTask() {
        return type + " | " + super.writeTask() + " | " + deadline;
//=======
//
//
//    @Override
//    String printTask() {
//        return type + super.printTask() + "(by:" +
//                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
//>>>>>>> branch-level-8
    }
}
