package duke.task;

/**
 * Represents a task that has some relation with time.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class TemporalTask extends Task {

    private String time;

    /**
     * Creates a temporal task with the given task description.
     *
     * @param taskDescription The description of the temporal task.
     * @param time The time which is related to the temporal task.
     */
    public TemporalTask(String taskDescription, String time) {
        super(taskDescription);
        this.time = time;
    }

    /**
     * Obtains the relation of time to the temporal task.
     *
     * @return The time relation.
     */
    abstract String getTimeRelation();

    @Override
    public String toString() {
        String taskRepresentation = super.toString();
        return String.format("%s (%s: %s)", taskRepresentation, getTimeRelation(), time);
    }
}
