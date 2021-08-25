package commands;

import bot.Bot;

/**
 * Abstract class for commands
 */
public abstract class Command {

    String[] args;

    /**
     * Set the command's arguments
     *
     * @param args command arguments
     */
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Get the command's arguments
     *
     * @return command arguments
     */
    public String[] getArgs() {
        return args;
    }

    /**
     * Run the command
     *
     * @param bot  the bot that invokes the command
     * @param args list of argument strings
     */
    public abstract void run(Bot bot, String[] args);

}
