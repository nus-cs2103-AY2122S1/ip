public class EmptyDescriptionError extends InvalidCommandException {
    public EmptyDescriptionError(String message) {
        super(String.format("The description of a %s cannot be empty.", message));
    }
}