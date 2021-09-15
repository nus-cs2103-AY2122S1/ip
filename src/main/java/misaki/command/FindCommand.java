package misaki.command;

import misaki.parser.Parser;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

/**
 * Represents a command class that find all tasks with matching keyword.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class FindCommand extends Command {

    /**
     * A constructor for FindCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public FindCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * Finds all tasks with matching keyword.
     *
     * @return String representation of a list of matching tasks.
     */
    public String find() {
        String keyword = parser.getKeyword();
        return ui.showMatchList(keyword, tasks);
    }
}
