package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime details;

    /**
     * Constuctor to create the Event Task
     * @param description The String explaining what needs to be done by the Task
     * @param details The date and time of the event
     */
    public EventTask(String description, String details) {
        super(description);
        this.details = LocalDateTime.parse(details, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Constuctor to create the Event Task
     * @param isCompleted The parameter indicates whether the task has been completed
     * @param description The String explaining what needs to be done by the Task
     * @param details The date and time of the event
     */
    public EventTask(String isCompleted, String description, String details) {
        super(isCompleted, description);
        this.details = LocalDateTime.parse(details, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getDetails() {
        return this.details.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String writeToFile() {
        return String.format("EVENT | %s | %s | %s\n", getIsCompleted(), getDescription(), getDetails());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                details.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")));
    }
}
