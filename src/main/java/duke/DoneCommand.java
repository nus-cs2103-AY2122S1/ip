package duke;

public class DoneCommand implements Command {
    private String userInput;

    public DoneCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(userInput.substring(5));
            Task curr = tasks.getTask(taskNum - 1);
            curr.markAsDone();
            return ui.showTaskDone(curr);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'done'!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number " + userInput.substring(5) + " does not exist!");
        }
    };

    @Override
    public boolean isExit() {
        return false;
    }
}
