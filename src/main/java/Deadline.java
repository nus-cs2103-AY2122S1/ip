public class Deadline extends Task {
    public Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
