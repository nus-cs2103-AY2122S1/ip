public class MissingDueDateDescriptionException extends Exception {

    public MissingDueDateDescriptionException() { }

    @Override
    public String toString() {
        return "OOPS!!! The due date of a deadline cannot be empty.";
    }
}
