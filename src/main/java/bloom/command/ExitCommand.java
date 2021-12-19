package bloom.command;

import bloom.app.Ui;
import bloom.constant.Message;

/**
 * Represents an exit command which stops the bot from running.
 * This command is used to terminate the bot.
 */
public class ExitCommand extends Command {

    /**
     * Stops the bot and says goodbye to users.
     */
    @Override
    public String run() {
        new Ui().stop();
        System.exit(0);
        return Message.COMMAND_EXIT.getMessage();
    }
}
