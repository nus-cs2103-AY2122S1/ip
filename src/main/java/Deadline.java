import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate toCompleteBy;
    protected String taskType;
    protected String time; //todo

    public Deadline(String description, LocalDate toCompleteBy, String time) {
        super(description);
        this.toCompleteBy = toCompleteBy;
        this.taskType = "D";
        this.time = time;
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    public static String getDeadlineDescription(String input) throws MissingDueDateDescriptionException {
        String[] strArr = input.split(" /by", 2);
        if (strArr.length < 2) {
            System.out.println("---------------------------------------------");
            System.out.println("OOPS!!! The due date of a deadline cannot be empty.");
            System.out.println("Use format \"/by yyyy-mm-dd {time}\" when providing due date.");
            System.out.println("---------------------------------------------");
            throw new MissingDueDateDescriptionException();
        } else {
            return strArr[0];
        }
    }

    public static LocalDate getDueDate(String input) {
        String[] strArr = input.split("/by ", 2);
        return LocalDate.parse(strArr[1]);
    }

    public static LocalDate getDate(String input) {
        String[] strArr = input.split("/by ", 2); // {desc, yyyy-mm-dd time}
        String[] arr = strArr[1].split(" ", 2); // {yyyy-mm-dd, time}
        return LocalDate.parse(arr[0]);
    }

    public static String getTime(String input) {
        String[] strArr = input.split("/by ", 2); // {desc, yyyy-mm-dd time}
        String[] arr = strArr[1].split(" ", 2); // {yyyy-mm-dd, time}
        return arr[1];
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString() +  " (by: "
                + this.toCompleteBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.time + ")";
    }
}
