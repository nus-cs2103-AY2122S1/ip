public class Deadlines extends Task {
    private String limit;

    public Deadlines(String description, String limit) {
        super(description);
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.limit + ")";
    }
}
