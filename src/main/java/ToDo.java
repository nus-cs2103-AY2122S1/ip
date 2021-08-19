public class ToDo extends Task{
    private static final String MISSING_TODO_MESSAGE = "Please input text after the todo command";

    private ToDo(String text) {
        super(text);
    }

    public static ToDo newTodo(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(MISSING_TODO_MESSAGE);
        }
        String todoText = input.trim();

        return new ToDo(todoText);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
