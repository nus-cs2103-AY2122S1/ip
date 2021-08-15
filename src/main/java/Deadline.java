import java.util.Scanner;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static String[] getDeadlineTaskAndTime(Scanner scanner) {
        return scanner.nextLine().split("\\s/by\\s");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}