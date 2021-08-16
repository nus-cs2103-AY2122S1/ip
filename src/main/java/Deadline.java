public class Deadline extends Task{
    private final String dateTime;
    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime);
    }
}
