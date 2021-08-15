public class DukeDeadlineTask extends DukeTask {
    final DukeDate deadline;

     DukeDeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = DukeDate.of(deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by %s)", super.toString(), deadline);
    }
}
