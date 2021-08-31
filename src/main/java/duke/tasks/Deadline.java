package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;
    private DateTimeFormatter printOut = DateTimeFormatter.ofPattern("MMM dd, E, yyyy");

    /**
     * Class Constructor
     * @param description Description of the Event
     * @param deadline Time of the event
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, Task.Type.D);
        this.deadline = deadline;
    }

    /**
     * Class Constructor when Event is read from localList.txt
     * @param description Description of the Event
     * @param deadline Time of the event
     * @param isDone Checks if the event is done
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description, Task.Type.D, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + printOut.format(deadline) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " " + deadline.toString();
    }
}
