public class Deadline extends Task {
    private String when;
    public Deadline(String str1, String str2) throws DukeException {
        super(str1);
        this.when = str2;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + when + ")";
    }
}