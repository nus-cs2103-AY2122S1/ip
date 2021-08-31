package duke;

public class FindCommand implements Command {
    private String userInput;

    public FindCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            TaskList found = new TaskList();
            for (int i = 0; i < tasks.numOfTasks(); i++) {
                Task curr = tasks.getTask(i);
                if (curr.checkDescription(userInput.substring(5))) found.addTask(curr);
            }
            return ui.showFoundTasks(found);
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
