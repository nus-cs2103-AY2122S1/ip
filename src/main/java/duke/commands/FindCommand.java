package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    /**
     * The command word that identifies a FindCommand instance.
     */
    public static final String COMMAND_WORD = "find";

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE =
            COMMAND_WORD + " <description> - displays all task with <description>\n" + "   Example: " + COMMAND_WORD
                    + " book";

    private String userCommand;

    /**
     * Instantiates FindCommand object.
     *
     * @param userCommand Full user input.
     */
    public FindCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Returns true if the command is an exit command, return false otherwise.
     *
     * @return true if the command is an exit command, return false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the respective command given and displays the result or error message(s).
     *
     * @param tasks TaskList that stores the list of tasks.
     * @param ui Ui instance that prints various messages.
     * @param storage Storage instance that reads and writes the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userCommand.length() < 5) {
                throw new IllegalArgumentException("Please add a description for the task you want to find!");
            }

            String desc = userCommand.substring(5);
            ui.printHorizontalLine();
            tasks.findTask(desc);
            ui.printHorizontalLine();
        } catch (IllegalArgumentException e) {
            ui.printError(e.getMessage());
        }

    }
}
