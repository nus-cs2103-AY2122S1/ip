public class Todo extends Task {
    public Todo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[T]" + checkBox + description;
    }

}
