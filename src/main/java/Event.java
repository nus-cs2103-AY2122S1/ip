public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String descAndTime) throws DukeException {
            this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        if (descAndTime.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your event needs a description.\n");
        }
        return descAndTime.split("at")[0];
    }

    private static String extractTime(String descAndTime) throws DukeException  {
        try {
            return descAndTime.split("at")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
