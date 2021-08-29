package duke;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * The Duke program implements an application that reads the user input
 * and does the corresponding actions based on the user input.
 */
public class Duke {
    private ChatBot bot;
    private Parser parser;

    public Duke() {

        bot = new ChatBot();
        parser = new Parser();
        // create object of Scanner to take inputs
//        Scanner sc = new Scanner(System.in);
        bot.start();
    }

    // This method is to return the string from user commands
    public String getResponse(String input) {
        String temp;
        try {
            temp = parser.parse(input, bot);
        } catch (DukeException e) {
            return bot.handleErrorMessage(e.getMessage());
        }
        return temp;
    }

    public String getStart() {
        return bot.handleStart();
    }
//        String input = sc.nextLine();
//        boolean running = true;
//
//        while (running) {
//            try {
//                parser.parse(input, bot);
//                int temp = bot.getExitStatus();
//                if (temp == 0) {
//                    running = false;
//                    break;
//                }
//                input = sc.nextLine();
//            } catch (DukeException e) {
//                bot.handleErrorMessage(e.getMessage());
//                input = sc.nextLine();
//            } catch (NumberFormatException e) {
//                bot.handleErrorMessage("Please enter a valid number after done");
//                input = sc.nextLine();
//            }
//        }
//        sc.close();
//    }
}


