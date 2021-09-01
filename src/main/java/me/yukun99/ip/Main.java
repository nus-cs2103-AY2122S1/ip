package me.yukun99.ip;

import java.io.IOException;

/**
 * Main class for our chat bot.
 */
public class Main {
    // Fancy name for my instance of help bot
    private static final String LOGO = " ____        _     \n"
            + "|  _ \\      | |    \n"
            + "| |_) | ___ | |__  \n"
            + "|  _ < / _ \\| '_ \\ \n"
            + "| |_) | (_) | |_) |\n"
            + "|____/ \\___/|_.__/ \n";
    private static final String FILEPATH = System.getProperty("user.dir");

    /**
     * Entry point for my help bot.
     *
     * @param args Command line arguments specified when running the program.
     */
    public static void main(String[] args) throws IOException {
        new HelpBot(LOGO, FILEPATH);
    }
}
