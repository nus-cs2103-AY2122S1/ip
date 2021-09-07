package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The HelpCommand class encapsulates the data and instructions
 * needed to provide guidance to the user on how to use Duke.
 */
public class HelpCommand extends Command {
    /** The user's input. */
    private String userInput;

    /**
     * Constructs a help command with information on what help the user needs.
     *
     * @param userInput The user's input.
     */
    public HelpCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the instructions for a help operation.
     *
     * @param taskList The task list currently loaded on Duke.
     * @param ui The object representing the UI of Duke.
     * @param storage The object representing the storage of the Duke program.
     * @return A string to be displayed to the user on the user interface.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandAndArgument = this.userInput.split(" ", 2);
        if (commandAndArgument.length < 2) {
            return ui.getGeneralHelpMessage();
        } else {
            return ui.getHelpMessageForCommand(commandAndArgument[1]);
        }
    }

    /**
     * Checks whether another object is equal with this help command.
     *
     * @param other The object being compared to.
     * @return true if both are help commands and share the same user input, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof HelpCommand) {
            HelpCommand otherCommand = (HelpCommand) other;
            return this.userInput.equals(otherCommand.userInput);
        } else {
            return false;
        }
    }
}
