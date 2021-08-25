package duke.command;

import duke.exception.InvalidCommandException;

import java.util.Scanner;

/**
 * This class handles the interactions with the user.
 */
public class Ui {
    private static final String LINE_HORIZONTAL =
            "___________________________________________________________";
    private final String BOT_NAME;
    private Parser parser;
    private static Scanner sc;

    /**
     * Constructor to create a new UI.
     *
     * @param name The name of the bot.
     */
    public Ui(String name) {
        this.BOT_NAME = name;
        sc = new Scanner(System.in);
        this.parser = new Parser();
    }

    /**
     * Prints initial message as prompt.
     */
    public void printWelcomeMessage() {
        System.out.printf("Greetings! This is %s.\n"
                + "What can I do for you?\n",
                this.BOT_NAME);
    }

    /**
     * Prints loading error message.
     */
    public void printLoadingError() {
        System.out.printf("%s\nIt seems there was an error reading the saved list.\n"
                + "Please ensure a duke.txt file is present in /data.\n"
                + "%s\n",
                LINE_HORIZONTAL, LINE_HORIZONTAL);
    }

    /**
     * Takes in user input and returns it.
     *
     * @return The trimmed input.
     */
    public String getInput() {
        return sc.nextLine().trim();
    }

    /**
     * Takes in user input and performs an action accordingly.
     *
     * @param input The user's input.
     * @throws InvalidCommandException If the command is unrecognised.
     */
    public String receiveUserCommand(String input) throws InvalidCommandException {
        return this.parser.interpretCommand(input);
    }

    /**
     * Prints the message for various types of exceptions.
     *
     * @param type The type of exception.
     */
    public void printException(String type) {
        switch (type) {
        case "InvalidCommand":
            System.out.printf("I don't quite understand what that means.\n"
                    + "Could you please rephrase that?\n"
                    + "%s\n", LINE_HORIZONTAL);
            break;
        case "IOException":
            System.out.printf("There is a problem with saving the list to the file.\n"
                    + "Please ensure a duke.txt file is present in /data.\n"
                    + "%s\n", LINE_HORIZONTAL);
            break;
        case "InvalidTask":
            System.out.printf("You might have mistyped the task number.\n"
                    + "Please recheck your task number and enter again.\n"
                    + "%s\n", LINE_HORIZONTAL);
            break;
        case "MissingTask":
            System.out.printf("You might have missed out on the task.\n"
                    + "Could you please enter it again?\n"
                    + "%s\n", LINE_HORIZONTAL);
            break;
        case "MissingTime":
            System.out.printf("You might have missed out on the time.\n"
                    + "Could you please enter it again?\n"
                    + "%s\n", LINE_HORIZONTAL);
            break;
        case "DateTimeParse":
            System.out.printf("Your date might not be in the correct format.\n" +
                    "Please ensure it is in the YYYY-MM-DD format.\n" +
                    "%s\n", LINE_HORIZONTAL);
            break;
        }
    }

    /**
     * Prints the line separator.
     */
    public void printSeparator() {
        System.out.println(LINE_HORIZONTAL);
    }

    /**
     * Prints the farewell message for the user.
     */
    public static void printBye() {
        System.out.printf("%s\n"
                        + "Goodbye. Hope to see you again soon!\n",
                LINE_HORIZONTAL);

        sc.close();
    }
}
