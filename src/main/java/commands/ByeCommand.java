package commands;

import bot.Bot;

/**
 * Command to exit the bot
 */
public class ByeCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        bot.stop();
    }

}
