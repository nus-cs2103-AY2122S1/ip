public class Deadline extends Task {
    private String dl = null;

    public Deadline() {}
    public Deadline(String desc) {
        super(desc);
    }
    public Deadline(String desc, String dl) {
        super(desc);
        this.dl = dl;
    }

    public void addDeadline(String dl) {
        this.dl = dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (dl != null ? String.format(" (by:%s)", dl) : "");
    }
}
