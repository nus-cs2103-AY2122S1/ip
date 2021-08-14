public class DukeDeadlineTask extends DukeTask {
    final String deadline;

    public DukeDeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("%s (by %s)", super.toString(), deadline);
    }
}
