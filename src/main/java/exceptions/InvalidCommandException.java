package exceptions;

public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I do not know what this means. ☹\n";
    }
}
