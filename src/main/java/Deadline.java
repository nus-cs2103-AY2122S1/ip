public class Deadline extends Task{
    private String dueDate;

    public Deadline(String text, String dueDate) {
        super(text);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }
}
