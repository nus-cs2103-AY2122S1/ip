public class Deadline extends Task{
    public Deadline(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
