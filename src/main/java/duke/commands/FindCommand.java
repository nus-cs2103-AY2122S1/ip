package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;

/**
 * Class to handle Find command.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Public constructor to create a new FindCommand with the keyword stored.
     *
     * @param keyword The keyword to be stored.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find task which contains keyword in the storage and prints it out.
     *
     * @param ui The Ui instance for printing.
     * @param storage The Storage instance to find the task from.
     * @return String to represent the reply of Duke.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return ui.reply(storage.find(keyword));
    }
}
