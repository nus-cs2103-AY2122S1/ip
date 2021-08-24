public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String writeToFile() {
        String s = "D" + " | ";
        if (this.isDone) {
            s += "1";
        } else {
            s += "0";
        }
        s = s + " | " + description + " | " + by;
        return s;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
