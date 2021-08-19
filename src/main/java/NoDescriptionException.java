public class NoDescriptionException extends Exception{

    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! The description of a " + message + " cannot be empty."));
    }
}

