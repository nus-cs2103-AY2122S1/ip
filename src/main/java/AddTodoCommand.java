public class AddTodoCommand extends AddCommand {

    private TaskList taskList;

    public AddTodoCommand(String input, TaskList taskList) {
        super(input, Task.Type.TODO);
        this.taskList = taskList;
    }

    /**
     * Adds a Todo event based on the user's input after verifying that the
     * user's input is valid.
     */
    @Override
    public void execute() {
        String details = this.removeFirstWordFromInput();
        if (details != null && this.verifyAddCommand(details.trim())) {
            Task task = Todo.newTodoTask(details);
            this.taskList.addTask(task);
        }
    }

    @Override
    public boolean verifyAddCommand(String input) {
        return input.length() > 0;
    }
}
