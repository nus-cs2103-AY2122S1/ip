package commands;

import bot.Bot;
import bot.Ui;

/**
 * Command to exit the bot
 */
public class ByeCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        bot.stop();
        return Ui.END_MESSAGES;
    }

}
