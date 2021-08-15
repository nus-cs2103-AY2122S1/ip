public class DukeDeadlineTask extends DukeTask {
    final DukeDate deadline;

     DukeDeadlineTask(String name, String deadline) {
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
        return String.format("%s/%d/by/%s", name, isDone ? 1 : 0, deadline);
    }
}
