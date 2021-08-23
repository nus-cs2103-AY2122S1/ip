package duke.util;

import java.util.Scanner;

public class Ui {
    private final String BORDER = "\t_________________________________________________\n";
    private final String MESSAGE_GREET = "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private final String MESSAGE_EXIT = "Goodbye!~";

    private final Scanner sc;

    public Ui() {
        // Initialize scanner and print welcome message
        sc = new Scanner(System.in);
        print(MESSAGE_GREET);
    }


    public String getCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Prints text between two horizontal lines.
     *
     * @param input the text to be printed.
     */
    public void print(String input) {
        String s = BORDER + "\t%s\n" + BORDER;
        System.out.printf(s, input.replaceAll("\n", "\n\t"));
    }

    /**
     * Prints goodbye.
     */
    public void close() {
        sc.close();
        print(MESSAGE_EXIT);
    }


}
