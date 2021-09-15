package duke.command;

import duke.DukeResponse;
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
     * Iterates through tasks and returns String describing each task in a formatted string.
     *
     * @param tasks TaskList from which tasks will be printed.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @return String describing each task in a formatted string.
     */
    private String getTasks(TaskList tasks, Ui ui) {
        String success = ui.getListSuccessMessage();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            // Increment i by 1 so number matches display indexing which starts from 1.
            int idx = i + 1;

            // Format should be "?. taskDescription\n"
            output.append(String.format("%d.%s%n", idx, tasks.get(i).toString()));
        }

        return success + "\n" + output;
    }

    /**
     * returns String describing all existing tasks.
     * Responds to user inputs of the format "list".
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Not Used. Storage that command executes upon.
     * @return DukeResponse containing string describing all existing tasks.
     */
    @Override
    public DukeResponse execute(TaskList tasks, Ui ui, Storable storage) {
        // Returns tasks in tasks.
        String output = this.getTasks(tasks, ui);
        return new DukeResponse(output, false);
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
