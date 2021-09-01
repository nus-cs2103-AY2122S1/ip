package pika.command;

import pika.ui.Storage;
import pika.ui.TaskList;
import pika.ui.Ui;

/**
 * ExitCommand class to handle the closing of the bot
 */
public class ExitCommand extends Command { //ExitCommand to handle the shutting down of bots

    /**
     * Constructor for the ExitCommand Class
     */
    public ExitCommand() {
        super(false);
    }

    /**
     * Executes the exitCommand for the bot to say the goodbye Message
     * and to inform the Duke Class that isRunning is false
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodBye();
    }
}
