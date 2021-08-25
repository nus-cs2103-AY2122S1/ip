package duke.tasks;

import java.time.LocalDateTime;

/**
 * Handles tasks that have a start and end date.
 */
public class EventTask extends Task {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Create a task based on the title, startDateTime and endDateTime
     *
     * @param title single line description of the task
     * @param startDateTime start of the event.
     * @param endDateTime end of the event
     */
    public EventTask(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(title, Type.EVENT);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String taskToString() {
        String dates = String.format(
                "%s - %s",
                DateParser.toDatabaseFormat(this.startDateTime),
                DateParser.toDatabaseFormat(this.startDateTime)
        );
        return super.taskToString() + dates;
    }

    @Override
    public String toString() {
        return String.format(
                "[E] %s (at: %s - %s)",
                super.toString(),
                DateParser.toHumanReadable(startDateTime),
                DateParser.toHumanReadable(endDateTime)
        );
    }
}
