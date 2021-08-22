public class AddDeadlineCommand extends AddCommand {

    private TaskList taskList;

    public AddDeadlineCommand(String input, TaskList taskList) {
        super(input, Task.Type.DEADLINE);
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        String details = this.removeFirstWordFromInput();
        if (details != null && this.verifyAddCommand(details.trim())) {
            Task task;
            try {
                task = Deadline.newDeadlineTask(details);
            } catch (DukeInvalidDateException e) {
                System.out.println(e.getMessage() + "\n");
                return;
            }
            this.taskList.addTask(task);
        }
    }

    /**
     * Verifies that the deadline task details are correct. It checks that the user has used the
     * command "-by" and that a non-empty date and time is specified. If it is not correct, it
     * prints an error message.
     *
     * @param input The deadline details.
     * @return True if the deadline details inputted by the user is correct. Otherwise, false.
     */
    @Override
    public boolean verifyAddCommand(String input) {
        if (!input.contains("-by") && !input.contains("/by")) {
            this.invalidArguments();
            return false;
        }
        String[] inputParts = input.split(" -by | /by ");
        if (inputParts.length != 2) {
            this.invalidArguments();
            return false;
        }
        return true;
    }
}
