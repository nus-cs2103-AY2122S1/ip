public class Deadline extends Task {
    protected String when;
    public Deadline(String str1, String str2) {
        super(str1);
        this.when = str2;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + when + ")";
    }
}