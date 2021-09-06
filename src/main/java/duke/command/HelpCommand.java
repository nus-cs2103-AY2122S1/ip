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

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandAndArgument = this.userInput.split(" ", 2);
        if (commandAndArgument.length < 2) {
            return ui.getGeneralHelpMessage();
        } else {
            return ui.getHelpMessageForCommand(commandAndArgument[1]);
        }
    }
}
