public class TaskTodo extends Task {
    public TaskTodo(String input) {
        super(input);
    }

    /**
     * String representation of to-do
     *
     * @return to-do display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return "[T]" + checkBox + description;
    }

}
