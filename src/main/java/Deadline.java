public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String descAndTime) throws DukeException {
        this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        if (descAndTime.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your deadline needs a description.\n");
        }
        return descAndTime.split("by")[0];
    }

    private static String extractTime(String descAndTime) throws DukeException {
        try {
            return descAndTime.split("by")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
