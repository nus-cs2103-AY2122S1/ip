public class Deadline extends Task {
    private String dl;
    public Deadline(String desc, String dl) {
        super(desc);
        this.dl = dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", dl);
    }
}
