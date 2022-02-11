package misaki.command;

import misaki.parser.Parser;
import misaki.storage.Storage;
import misaki.tasklist.TaskList;
import misaki.ui.Ui;

/**
 * Represents a command class that quit the bot.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class ByeCommand extends Command {

    /**
     * A constructor for ByeCommand.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public ByeCommand(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        super(tasks, parser, storage, ui);
    }

    /**
     * Returns bye message.
     *
     * @return String message from bot to say goodbye.
     */
    public String bye() {
        return ui.showBye();
    }
}
