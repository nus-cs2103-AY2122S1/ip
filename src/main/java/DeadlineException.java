public class DeadlineException extends HAL9000Exception{
    public DeadlineException() {
        super("The description of a deadline cannot be empty.");
    }
    public DeadlineException(String str) {
        super(str);
    }
}
