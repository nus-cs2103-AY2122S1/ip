package misaki.command;

import misaki.parser.Parser;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

public class HelpCommand extends Command {

    /**
     * A constructor for HelpCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public HelpCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * Returns help message.
     *
     * @return String message to help guide user.
     */
    public String help() {
        return ui.showHelp();
    }
}
