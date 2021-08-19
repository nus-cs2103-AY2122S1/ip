public class Todo extends Task {

    public static Task create(String userParams) {
        String description = userParams;
        return new Todo(description);
    }
    private Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
