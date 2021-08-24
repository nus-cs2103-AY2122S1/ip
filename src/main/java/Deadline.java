public class Deadline extends Task{
    private String by;

    public Deadline(String input, String by) {
        super(input);
        this.by = by;
    }

    @Override
    public String formatTask() {
        return "D" + " | " + super.formatTask() + " | " + this.by;
    }

    @Override
    public  String toString() {
        return "[D] " + super.toString() + "(by: " + this.by + ")";
    }
}
