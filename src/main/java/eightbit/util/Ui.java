package eightbit.util;

import eightbit.EightBitException;

import java.util.Scanner;

/**
 * Responsible for accepting user inputs and printing messages.
 */
public class Ui {

    /**
     * Wraps the message with lines and print it.
     *
     * @param msg Message to be printed.
     */
    public void printWithLines(String msg) {
        String LINE = "-------------------------------------------------------";
        System.out.println(LINE + "\n" + msg + "\n" + LINE);
    }

    /**
     * Prints a welcome message on start.
     */
    public void showWelcome() {
        printWithLines("Hello! I'm 8-Bit!\nWhat can I do for you?");
    }

    /**
     * Prints an exit message when the program terminates.
     */
    public void showBye() {
        printWithLines("Bye. Hope to see you again soon!");
    }

    /**
     * Reads user input.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    /**
     * Prints an error message represented by the exception.
     *
     * @param e Exception containing the error message.
     */
    public void printError(EightBitException e) {
        printWithLines(e.toString());
    }
}
