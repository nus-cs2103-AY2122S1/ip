public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline build(String desc_date) {
        desc_date = desc_date.replaceAll("\\(by: (.*)\\)", "/by $1");
        String[] input = desc_date.split(" /by ",2);
        return new Deadline(input[0], input[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}