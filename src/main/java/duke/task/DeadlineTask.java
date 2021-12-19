package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor to create a Deadline Task
     * @param description The String explaining what needs to be done by the Task
     * @param deadline The due date and time of the Task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Constructor to create a Deadline Task
     * @param isCompleted The parameter indicates whether the task has been completed
     * @param description The String explaining what needs to be done by the Task
     * @param deadline The due date and time of the Task
     */
    public DeadlineTask(String isCompleted, String description, String deadline) {
        super(isCompleted, description);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String writeToFile() {
        return String.format("DEADLINE | %s | %s | %s\n", getIsCompleted(), getDescription(), getDeadline());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")));
    }
}
