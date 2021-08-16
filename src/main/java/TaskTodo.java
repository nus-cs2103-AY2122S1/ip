public class TaskTodo extends Task {
    public TaskTodo(String input) {
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
