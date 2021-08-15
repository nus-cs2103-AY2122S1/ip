package duke.task;

public class EventTask extends TemporalTask {
    private static final String TYPE_MARK = "E";
    private static final String TIME_RELATION = "at";

    /**
     * Creates an event task with the given task description.
     *
     * @param taskDescription The description of the event task.
     * @param time The time the event occurs.
     */
    public EventTask(String taskDescription, String time) {
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
