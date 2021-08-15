package duke.task;

/**
 * Represents a task with a deadline.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DeadlineTask extends TemporalTask {
    private static final String TYPE_MARK = "D";
    private static final String TIME_RELATION = "by";

    /**
     * Creates a task with a deadline with the given task description.
     *
     * @param taskDescription The description of the task with a deadline.
     * @param time The deadline.
     */
    public DeadlineTask(String taskDescription, String time) {
        super(taskDescription, time);
    }

    @Override
    String getTypeIndicator() {
        return String.format("[%s]", TYPE_MARK);
    }

    @Override
    String getTimeRelation() {
        return TIME_RELATION;
    }
}
