package duke;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * The Duke program implements an application that reads the user input
 * and does the corresponding actions based on the user input.
 */
public class Duke {

    /**
     * This is the main method which is used to run the application.
     *
     * @param args
     */
    public static void main(String[] args) {

        ChatBot bot = new ChatBot();
        Parser parser = new Parser();
        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        bot.start();
        String input = sc.nextLine();
        boolean running = true;

        while (running) {
            try {
                parser.parse(input, bot);
                int temp = bot.getExitStatus();
                if (temp == 0) {
                    running = false;
                    break;
                }
                input = sc.nextLine();
            } catch (DukeException e) {
                bot.handleErrorMessage(e.getMessage());
                input = sc.nextLine();
            } catch (NumberFormatException e) {
                bot.handleErrorMessage("Please enter a valid number after done");
                input = sc.nextLine();
            }
        }
        sc.close();
    }
}


