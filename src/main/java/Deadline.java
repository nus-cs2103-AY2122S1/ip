public class Deadline extends Task{
    private String date;

    public Deadline(String deadline, String date) {
        super(deadline, "D", date);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", super.getTaskSymbol(), super.toString(), this.date);
    }
}
