public class Deadline extends Task{
    public Deadline(String label) {
        super(label);
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
