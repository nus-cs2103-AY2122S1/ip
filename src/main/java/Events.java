public class Events extends Task {
    private String limit;

    public Events(String description, String limit) {
        super(description);
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.limit + ")";
    }
}
