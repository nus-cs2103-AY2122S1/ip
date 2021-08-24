package duke;

public class DeleteCommand implements Command {
    private String userInput;

    public DeleteCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(userInput.substring(7));
            Task curr = tasks.removeTask(taskNum - 1);
            ui.showDeleteTask(curr, tasks.numOfTasks());
            storage.saveTasks(tasks);
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'delete'!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number " + userInput.substring(7) + " does not exist!");
        }
    };

    @Override
    public boolean isExit() {
        return false;
    }
}
