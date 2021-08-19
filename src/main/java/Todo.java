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
    private Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
