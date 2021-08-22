public class DeleteCommand implements Command {

    private String input;
    private TaskList taskList;

    public DeleteCommand(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Removes a task from the taskList based on the user's input.
     */
    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.taskList.removeTask(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.invalidArguments();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArguments() {
        System.out.println("Invalid argument to the \"delete\" function.\n");
    }
}
