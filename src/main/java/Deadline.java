import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate taskDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Deadline(String taskName, LocalDate taskDate, boolean isDone) {
        super(taskName, TaskType.DEADLINE, isDone);
        this.taskDate = taskDate;
    }

    public Deadline(String taskName, LocalDate taskDate) {
        this(taskName, taskDate, false);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), taskDate.format(formatter));
    }
}
