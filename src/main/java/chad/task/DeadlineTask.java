package chad.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DeadlineTask extends TemporalTask {

    private static final String TYPE_MARK = "D";
    private static final String TIME_RELATION = "by";
    private static final String FILE_REPRESENTATION_TEMPLATE = "%s %d %s /%s %s";
    private static final String TYPE_INDICATOR_TEMPLATE = "[%s]";
    private static final int DONE_FILE_REPRESENTATION = 1;
    private static final int NOT_DONE_FILE_REPRESENTATION = 0;

    /**
     * Creates a task with a deadline with the given task description.
     *
     * @param taskDescription The description of the task with a deadline.
     * @param time The deadline.
     */
    public DeadlineTask(String taskDescription, LocalDateTime time) {
        super(taskDescription, time);
    }

    @Override
    public String getFileRepresentation() {
        int done = isDone() ? DONE_FILE_REPRESENTATION : NOT_DONE_FILE_REPRESENTATION;
        String description = getDescription();
        String time = getFileFormattedTime();
        return String.format(FILE_REPRESENTATION_TEMPLATE, TYPE_MARK, done, description, TIME_RELATION, time);
    }

    @Override
    String getTypeIndicator() {
        return String.format(TYPE_INDICATOR_TEMPLATE, TYPE_MARK);
    }

    @Override
    String getTimeRelation() {
        return TIME_RELATION;
    }
}
