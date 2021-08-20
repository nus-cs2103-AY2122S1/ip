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
    public Deadline(String desc, String dl, boolean done) {
        super(desc, done);
        this.dl = dl;
    }

    public void addDeadline(String dl) {
        this.dl = dl;
    }
    
    public String toDB() {
        return String.format("D | %d | %s | %s", super.done ? 1 : 0, super.desc, dl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + (dl != null ? String.format(" (by:%s)", dl) : "");
    }
}
