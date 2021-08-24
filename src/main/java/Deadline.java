import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    protected String deadline;
    protected LocalDate deadlineTime;

    Deadline(String description) {
        super(description);
        this.deadline = "";
        this.deadlineTime = null;
    }

    Deadline(String description, boolean isDone) {
        super(description, isDone);
        this.deadline = "";
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.deadlineTime = setDeadlineTime(deadline);
    }

    public String getDeadline() {
        if (deadlineTime == null) {
            return this.deadline;
        } else {
            return this.deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    public LocalDate setDeadlineTime(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Wrong format of deadline. Deadline must be in the format of YYYY-MM-DD.");
            return null;
        }
    }

    @Override
    public String saveTaskToFile() {
        return typeOfTask() + "||" + getStatusIcon() + "||" + this.getDescription() + "||" + this.getDeadline();
    }

    @Override
    public String typeOfTask() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", this.typeOfTask(),
                this.getStatusIcon(), this.getDescription(), this.getDeadline());
    }
}
