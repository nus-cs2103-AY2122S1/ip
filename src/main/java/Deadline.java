public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getType() {
        return "D";
    }

    public static boolean isValid(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        return true;
    }

    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.date);
    }
}
