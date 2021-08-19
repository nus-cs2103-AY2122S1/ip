package tasks;

public class Deadline extends Task {
    private String dueDate;

    private Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public static Deadline addDeadline(String input) {
        String[] arr = input.split(" /by ");

        Deadline item = new Deadline(arr[0], arr[1]);

        return item;
    }

    @Override
    public String toString() {
        String due = "(by: " + this.dueDate + ")";

        String res = "[D] [" + this.getStatus() + "] " + this.description + " " + due;

        return res;
    }
}
