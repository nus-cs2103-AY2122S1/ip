public class Deadline extends Task{
    public Deadline(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
