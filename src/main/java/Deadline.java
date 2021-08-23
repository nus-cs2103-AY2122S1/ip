public class Deadline extends Task{
    private final String dateTime;

    public Deadline(boolean done, String name, String dateTime) {
        super(done, name);
        this.dateTime = dateTime;
    }

    @Override
    public String encode() {
        return String.format("D|%s|%s", super.encode(), dateTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dateTime);
    }
}
