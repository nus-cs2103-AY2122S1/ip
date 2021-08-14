public class InvalidTaskTypeException extends Exception {
    public InvalidTaskTypeException(String description) {
        super(String.format(
            "'%s' is not a valid task type. " +
            "Tasks should begin with one of the following: todo, deadline, event",
            description
        ));
    }
}
