import java.util.Scanner;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public static String[] getEventTaskAndTime(Scanner scanner) {
        String[] taskTime = scanner.nextLine().split("\\s/at\\s");
        return taskTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
