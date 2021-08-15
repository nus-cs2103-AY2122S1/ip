public class Event extends Task{

    protected String returnDate;

    public Event(String description, String returnDate, Boolean empty) throws DukeException {
        super(description);
        this.returnDate = returnDate;

        if(empty) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), this.returnDate);
    }
}
