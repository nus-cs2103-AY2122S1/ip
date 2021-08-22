package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has some relation with time.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class TemporalTask extends Task {

    private static final String DATE_TIME_FORMAT_PATTERN = "HH:mm MMMM d yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);

    private LocalDateTime time;

    /**
     * Creates a temporal task with the given task description.
     *
     * @param taskDescription The description of the temporal task.
     * @param time The time which is related to the temporal task.
     */
    public TemporalTask(String taskDescription, LocalDateTime time) {
        super(taskDescription);
        this.time = time;
    }

    /**
     * Obtains the relation of time to the temporal task.
     *
     * @return The time relation.
     */
    abstract String getTimeRelation();

    String getTime() {
        return time;
    }

    @Override
    public String toString() {
        String taskRepresentation = super.toString();
        return String.format("%s (%s: %s)", taskRepresentation, getTimeRelation(), time.format(DATE_TIME_FORMATTER));
    }
}
