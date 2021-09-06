package commands;

import duke.Ui;
import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

/**
 * A command for users to search for tasks in the taskList by specifying
 * a keyword. The command will then look for tasks with names that contain
 * the specified keyword.
 */
public class FindCommand extends Command {

    private final String input;
    private final TaskList taskList;

    /**
     * Creates a find command to find a task in Duke's taskList.
     *
     * @param input The user input that triggers this command.
     * @param taskList The taskList to search through.
     */
    public FindCommand(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        try {
            // Checks if a keyword is provided
            String keyword = input.split(" ", 2)[1].trim();
            this.displaySearchResults(this.taskList.findTask(keyword));
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return "Please provide a keyword to search for a task.\n";
    }

    private void displaySearchResults(ArrayList<Task> searchResults) {
        StringBuilder message;
        if (searchResults.isEmpty()) {
            message = new StringBuilder("Sorry I could not find any matching tasks.\n");
            return;
        }
        message = new StringBuilder(Ui.DASHES);
        message.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < searchResults.size(); i++) {
            message.append(i + 1).append(". ").append(searchResults.get(i)).append("\n");
        }
        message.append(Ui.DASHES);
        this.setExecutionMessage(message.toString());
    }
}
