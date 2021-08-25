package eightbit.util;

import eightbit.EightBitException;

import java.util.Scanner;

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

    public void showWelcome() {
        printWithLines("Hello! I'm 8-Bit!\nWhat can I do for you?");
    }

    public void showBye() {
        printWithLines("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    public void printError(EightBitException e) {
        printWithLines(e.toString());
    }
}
