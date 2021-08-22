public class Deadline extends Task {
    
    private final String identifier = "D";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String result = "[" + identifier + "]";
        result += super.toString();
        result += " (by: " + this.deadline + ")";
        return result;
    }

    @Override
    public String databaseString() {
        String result = identifier + "|";
        result += getStatus() ? "1|" : "0|";
        result += getDescription() + "|" + this.deadline;
        return result;
    }
}
