package duke.command;

import duke.Storable;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that can be executed to print all existing tasks.
 */
public class ListCommand extends Command {
    private final String userInput;

    /**
     * Constructor for ListCommand.
     * Creates a ListCommand with user input.
     *
     * @param userInput User's input into Duke chatbot.
     */
    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Iterates through tasks and prints each task in a formatted string.
     *
     * @param tasks TaskList from which tasks will be printed.
     * @param ui Ui to get enums, response messages and exception messages from.
     */
    private void printTasks(TaskList tasks, Ui ui) {
        ui.showListSuccess();

        for (int i = 0; i < tasks.size(); i++) {
            // Increment i by 1 so number matches display indexing which starts from 1.
            int idx = i + 1;

            // Format should be "?. taskDescription\n"
            System.out.printf("%d.%s%n", idx, tasks.get(i).toString());
        }
    }

    /**
     * Prints all existing tasks.
     * Responds to user inputs of the format "list".
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Not Used. Storage that command executes upon.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        // Prints tasks in tasks.
        this.printTasks(tasks, ui);
    }

    /**
     * Indicates whether another object is equals to this ListCommand.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this ListCommand.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCommand) {
            ListCommand other = (ListCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
