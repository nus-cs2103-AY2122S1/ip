package misaki.command;

import misaki.parser.Parser;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

/**
 * Represents a command class that list out all tasks.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class ListCommand extends Command {

    /**
     * A constructor for ListCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public ListCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * List out all tasks.
     *
     * @return String representation of the list of tasks.
     */
    public String list() {
        return ui.showList(tasks);
    }
}
