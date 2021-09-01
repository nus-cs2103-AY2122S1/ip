package bloom.app;

import java.util.Scanner;

import bloom.constant.Drawing;
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
     */

    public void run(String userInput) {
        Parser parser = new Parser();
        System.out.println(Drawing.HORIZONTAL_LINE.getDrawing());
        try {
            parser.parse(userInput).run();
        } catch (BloomUnknownCommandException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Drawing.HORIZONTAL_LINE.getDrawing());
    }

    /**
     * Stops the bot.
     */

    public void stop() {
        Ui.isRunning = false;
    }
}
