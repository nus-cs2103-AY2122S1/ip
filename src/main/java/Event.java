public class Event extends Task{

    protected String duration;

    public Event(String restOfInput, Boolean empty) throws DukeException {
        super(restOfInput);

        if(empty || restOfInput.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (restOfInput.contains("/at")) {
            description = restOfInput.split("/at",2)[0];
            duration = restOfInput.split("/at",2)[1];
            if (description.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (duration.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
        }
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), this.duration);
    }
}
