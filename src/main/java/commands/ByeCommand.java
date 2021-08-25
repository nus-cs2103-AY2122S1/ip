package commands;

import bot.Bot;

public class ByeCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        bot.stop();
    }

}
