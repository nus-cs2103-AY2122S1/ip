public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            this.description = description.substring(1);
        }

        if (by.isEmpty() || by == "" || by == " ") {
            throw new DukeException("☹ OOPS!!! The deadline of this task must be indicated.");
        } else {
            this.by = by.substring(1);
        }
    }

    public String getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String addOns() {
        return this.by;
    }
}
