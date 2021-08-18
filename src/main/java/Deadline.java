public class Deadline extends Task {
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        String isDone = isDone() ? "x" : " ";
        return String.format("[D][%s] %s (by: %s)", isDone, getName(), getEndDate());
    }
}
