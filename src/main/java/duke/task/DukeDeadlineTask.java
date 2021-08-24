package duke.task;

public class DukeDeadlineTask extends DukeTask {
    private final DukeDate deadline;

    public DukeDeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = DukeDate.of(deadline);
    }

    public DukeDeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = DukeDate.of(deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by %s)", super.toString(), deadline);
    }

    @Override
    public String toSerializedString() {
        return String.format("%s/%d/by/%s", name, done ? 1 : 0, deadline);
    }
}
