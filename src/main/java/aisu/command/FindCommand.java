package aisu.command;

import aisu.Storage;
import aisu.TaskList;
import aisu.Ui;

/**
 * Command to find tasks with a keyword from the tasklist.
 *
 * @author Liaw Xin Yan
 */
public class FindCommand extends Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input.trim();
    }

    /**
     * Finds tasks with a keyword and displays them to user.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        String result = tasklist.findTasksWith(this.input);
        this.uiText = ui.formatText("Here's what I found:", result);
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
