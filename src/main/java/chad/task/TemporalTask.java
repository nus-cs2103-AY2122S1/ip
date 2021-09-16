package chad.task;

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
    private static final String DATE_TIME_FILE_FORMAT_PATTERN = "yyyyMMddHHmm";
    private static final DateTimeFormatter DATE_TIME_FILE_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_TIME_FILE_FORMAT_PATTERN);
    private static final String TEMPORAL_TASK_STRING_REPRESENTATION_TEMPLATE = "%s (%s: %s)";

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

    String getFileFormattedTime() {
        return time.format(DATE_TIME_FILE_FORMATTER);
    }

    @Override
    public String toString() {
        String taskRepresentation = super.toString();
        return String.format(TEMPORAL_TASK_STRING_REPRESENTATION_TEMPLATE, taskRepresentation, getTimeRelation(),
                time.format(DATE_TIME_FORMATTER));
    }
}
