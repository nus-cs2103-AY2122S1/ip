public class DukeDeadlineTask extends DukeTask {
    final String deadline;

     DukeDeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public DukeDeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
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
