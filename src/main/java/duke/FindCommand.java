package duke;

/**
 * A class that handles finding tasks.
 */
public class FindCommand implements Command {
    private String userInput;

    /**
     * A constructor for FindCommand object.
     *
     * @param userInput input from user
     */
    public FindCommand(String userInput) {
        super();
        this.userInput = userInput;
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList found = new TaskList();
        for (int i = 0; i < tasks.numOfTasks(); i++) {
            Task curr = tasks.getTask(i);
            if (curr.checkDescription(userInput.substring(5))) {
                found.addTask(curr);
            }
        }
        return ui.getFoundTasks(found);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
