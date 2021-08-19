public class Deadline extends Task {
    String by;
    public Deadline(String desc, String by) throws Exception{
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
