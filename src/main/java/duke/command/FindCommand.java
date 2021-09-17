package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "find";

    private String searchTerm;

    /**
     * Returns the Find command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return Find command.
     */
    public static Command create(String userInput)  {
        assert userInput != null : "User input should be null for the creation of a Command";

        String searchTerm = userInput.split(" ", 2)[1];
        return new FindCommand(searchTerm);
    }

    private FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        TaskList filteredTasks = tasks.findTasks(searchTerm);
        return ui.showFilteredTaskList(filteredTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
