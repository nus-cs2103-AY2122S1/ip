package bloom.app;

import java.util.Scanner;

import bloom.exception.command.BloomUnknownCommandException;

/**
 * Represents the user interface, controls basic action
 * such as start, run, stop of the bot.
 */
public class Ui {

    /** The current state of the bot. */
    private static boolean isRunning = true;

    /**
     * Starts the bot.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        run("greet");
        String userInput;
        while (Ui.isRunning) {
            userInput = scanner.nextLine();
            run(userInput);
        }
    }

    /**
     * Processes whatever inputted by users into the bot.
     *
     * @param userInput the user input
     * @return          the string response
     */
    public String run(String userInput) {
        Parser parser = new Parser();
        try {
            return parser.parse(userInput).run();
        } catch (BloomUnknownCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Stops the bot.
     */
    public void stop() {
        Ui.isRunning = false;
    }
}
