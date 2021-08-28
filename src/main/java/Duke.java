package main.java;

import main.java.bot.Bot;

/**
 * A class that represents the main Duke bot.
 * The main method, once run, will start the bot.
 */
public class Duke {

    /**
     * Starts the main Duke bot.
     *
     * @param args The list of arguments given to the main method.
     */
    public static void main(String[] args) {
        Bot bot = new Bot();
        bot.start();
    }
}