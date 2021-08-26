package duke.commands;

import duke.Ui;
import duke.storage.Storage;

/**
 * Class to handle Find command
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Public constructor to create a new FindCommand with the keyword stored
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find task which contains keyword in the storage and prints it out
     *
     * @param ui The Ui instance for printing
     * @param storage The Storage instance to find the task from
     * @return A boolean of false to indicate the main while loop should not be broken
     */
    @Override
    public boolean execute(Ui ui, Storage storage) {
        ui.print(storage.find(keyword));
        return false;
    }
}
