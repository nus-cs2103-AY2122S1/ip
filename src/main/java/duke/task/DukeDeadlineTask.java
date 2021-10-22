package duke.task;

public class DukeDeadlineTask extends DukeTask {
    private final DukeDate deadline;

    /**
     * Creates a task with a name and a deadline. The task is marked as not done.
     * @param name The name of the event
     * @param deadline The deadline of the event
     */
    public DukeDeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = DukeDate.of(deadline);
    }


    /**
     * Creates a task with a name, deadline and whether or not it is marked as done.
     * @param name THe name of the task
     * @param isDone If the event is marked as done
     * @param deadline The deadline of the task
     */
    public DukeDeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = DukeDate.of(deadline);
    }

    /**
     * Returns the deadline of the task.
     * @return the deadline of the task
     */
    public DukeDate getDeadline() {
        return deadline;
    }

    @Override
    public String toCliString() {
        return String.format("%s (by %s)", super.toString(), deadline);
    }

    @Override
    public String toSerializedString() {
        return String.format("%s/%d/by/%s", getName(), isDone() ? 1 : 0, deadline);
    }
}
