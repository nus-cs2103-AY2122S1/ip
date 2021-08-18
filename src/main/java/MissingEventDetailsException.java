public class MissingEventDetailsException extends Exception {
    public MissingEventDetailsException() { }

    @Override
    public String toString() {
        return "OOPS!!! The details of an event cannot be empty.";
    }
}
