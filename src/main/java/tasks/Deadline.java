package tasks;

public class Deadline extends Task {
    private String dueDate;

    private Deadline(String description, boolean completed, String dueDate) {
        super(description, completed);
        this.dueDate = dueDate;
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
            format += "0 | ";
        } else {
            format += "1 | ";
        }

        format += this.description;
        format += this.dueDate;

        return format;
    }

    @Override
    public String toString() {
        String due = "(by: " + this.dueDate + ")";

        String res = "[D] [" + this.getStatus() + "] " + this.description + " " + due;

        return res;
    }
}