package exception;

public class MessageEmptyException extends DukeException {
    public MessageEmptyException() {
        super("You forgot to enter a message after the command!");
    }
}
