package tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate dueDate;

    private Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = Task.formatDate(dueDate);
    }

    public static Deadline addDeadline(String input) {
        String[] arr = input.split(" /by ");

        Deadline item = new Deadline(arr[0], arr[1]);

        return item;
    }

    @Override
    public String toString() {
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String due = "(by: " + date + ")";

        String res = "[D] [" + this.getStatus() + "] " + this.description + " " + due;

        return res;
    }
}