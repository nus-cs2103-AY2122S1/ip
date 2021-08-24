package duke.main;

import java.util.Scanner;

import duke.task.Storage;

/**
 * Encapsulates all the methods that is responsible for User Interaction (UI).
 */
public class Ui {

    /**
     * Constructs a Ui instance.
     * Automatically prints the logo.
     */
    public Ui() {
        showLogo();
    }

    /**
     * Prints the introduction and logo.
     */
    public static void showLogo() {
        String logo = " _______       ___      _______   __     __   _\n"
                + "|   ____|     / ^ \\     |   _  \\  \\ \\   / /  | |\n"
                + "|  | ___     / /_\\ \\    |  |_|  |  \\ \\ / /   | |\n"
                + "|  ||_  |   /  ___  \\   |  __  <    \\   /    |_|\n"
                + "|  |__| |  /  /   \\  \\  |  | \\  \\    | |      _ \n"
                + "|_______| /__/\t   \\__\\ |--|  \\--\\   |_|     |_|\n";

        printDoubleDivider();
        System.out.println("Hello! My name is\n" + logo);
        printDoubleDivider();
        System.out.println("How may I help you?");
        printDoubleDivider();
    }

    /**
     * Prompts the user for input and carries out actions accordingly.
     * Additionally, saves contents of TaskList in hard disk after every input.
     *
     * @param sc Scanner to obtain standard input from user.
     * @param parser Interprets user input and carries out actions accordingly.
     * @param storage Storage responsible for storing the Tasks in the TaskList after every input.
     */
    public void getInput(Scanner sc, Parser parser, Storage storage) {
        System.out.print("Input: ");
        boolean isContinue = parser.handleInput(sc.nextLine());

        // After every input, write to disk file
        storage.writeToFile();

        if (isContinue) {
            getInput(sc, parser, storage);
        }
    }

    /**
     * Prints a divider using a line of "=".
     */
    public static void printDoubleDivider() {
        System.out.println("\n=================================================\n");
    }

    /**
     * Prints a divider using a line of "-".
     */
    public static void printSingleDivider() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }
}
