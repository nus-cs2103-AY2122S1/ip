public class Todo extends Task {

    public static Task create(String userInput) throws MalformedCommandException {
        try {
            String description = userInput.split(" ", 2)[1];
            return new Todo(description);
        } catch(ArrayIndexOutOfBoundsException e){
            throw new MalformedCommandException("Creating an todo needs to follow the following format: " +
                "todo [description]");
        }
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    public Todo(String description) {
        super(description);
    }

    private String identifier() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + identifier() + "]" + super.toString();
    }

    public String formatForStorage() {
        return identifier() + STORAGE_DELIMITER + super.formatForStorage();
    }
}
