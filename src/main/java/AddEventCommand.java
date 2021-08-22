public class AddEventCommand extends AddCommand {

    private TaskList taskList;

    public AddEventCommand(String input, TaskList taskList) {
        super(input, Task.Type.EVENT);
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        String details = this.removeFirstWordFromInput();
        if (details != null && this.verifyAddCommand(details.trim())) {
            Task task;
            try {
                task = Event.newEventTask(details);
            } catch (DukeInvalidDateException e) {
                System.out.println(e.getMessage() + "\n");
                return;
            }
            this.taskList.addTask(task);
        }
    }

    /**
     * Verifies the event task details is correct. It checks that the user has used the command
     * "-at" and that a non-empty date and time is specified. If is not correct, it prints
     * an error message.
     *
     * @param input The event details.
     * @return True if the event details inputted by the user is correct. Otherwise, false.
     */
    @Override
    public boolean verifyAddCommand(String input) {
        if (!input.contains("-at") && !input.contains("/at")) {
            this.invalidArguments();
            return false;
        }
        String[] inputParts = input.split(" -at | /at ");
        if (inputParts.length != 2) {
            this.invalidArguments();
            return false;
        }
        return true;
    }
}
