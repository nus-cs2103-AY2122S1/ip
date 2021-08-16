public class Deadline extends Task{
    protected final String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public Deadline markAsDone() {
        return new Deadline(super.getName(), true, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (deadline: %s)", super.toString(), deadline);
    }
}
