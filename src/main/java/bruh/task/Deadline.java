package bruh.task;

public class Deadline extends TimedTask {
    public Deadline(String description, String deadline) {
        super(description, 'D', deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDateTimeDesc());
    }
}