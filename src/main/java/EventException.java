public class EventException extends HAL9000Exception{
    public EventException() {
        super("The description of an event cannot be empty.");
    }
    public EventException(String str) {
        super(str);
    }
}
