package bubbles.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate dueDate;

    private Deadline(String description, boolean completed, String dueDate) {
        super(description, completed);
        this.dueDate = Task.formatDate(dueDate);
    }

    public static Deadline addDeadline(String input, boolean completed) {
        String[] arr = input.split(" /by ");

        Deadline item = new Deadline(arr[0], completed, arr[1]);

        return item;
    }

    @Override
    public String format() {
        String format = "D | ";

        if (this.isDone) {
            format += "1 | ";
        } else {
            format += "0 | ";
        }

        format += this.description;
        format += " | by ";
        format += this.dueDate;

        return format;
    }

    @Override
    public String toString() {
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String due = "(by: " + date + ")";

        String res = "[D] [" + this.getStatus() + "] " + this.description + " " + due;

        return res;
    }
}