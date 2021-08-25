public class Deadline extends Task {
    String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toStringInStorage() {
        int done = this.isDone ? 1 : 0;
        return String.format("D/%d/%s/%s", done, this.name, this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by:%s)", super.toString(), this.by);
    }
}
