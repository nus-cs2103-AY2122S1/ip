public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            this.description = description.substring(1);
        }

        if (at.isEmpty() || at == "" || at == " ") {
            throw new DukeException("☹ OOPS!!! The time of the event must be indicated.");
        } else {
            this.at = at.substring(1);
        }
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String addOns() {
        return this.at;
    }
}
