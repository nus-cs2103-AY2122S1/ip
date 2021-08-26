public class EventException extends DukeException1 {
    EventException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a event cannot be empty.";
    }
}
