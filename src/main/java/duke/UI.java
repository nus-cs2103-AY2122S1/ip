package duke;

import java.util.Scanner;

/** deals with interactions with the user */
public class UI {

    /** scanner for I/O */
    Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome text
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * read user input
     * @return returns user input as string
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print the text to I/O
     * @param text text to print
     */
    public void printText(String text) {
        System.out.println(text + "\n");
    }
}
