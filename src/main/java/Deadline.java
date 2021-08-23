public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toDataString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, this.description, this.date);
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
