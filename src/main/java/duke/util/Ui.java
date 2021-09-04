package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "\t_________________________________________________\n";
    private static final String GREET = "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private static final String EXIT = "Goodbye!~";
    private static final String HELP = "Supported commands:\n" +
            "todo, deadline, event, done, delete, find, list, bye";

    private final Scanner sc;

    /**
     * Initialise scanner and greet user.
     */
    public Ui() {
        sc = new Scanner(System.in);
        print(GREET);
    }

    /**
     * Returns a {@code string} which is the trimmed user input.
     * @return Trimmed user input.
     */
    public String getCommand() {
        assert sc != null : "Scanner should not be null";
        return sc.nextLine().trim();
    }

    /**
     * Prints text between two horizontal lines.
     *
     * @param input the text to be printed.
     */
    public void print(String input) {
        String s = LINE + "\t%s\n" + LINE;
        System.out.printf(s, input.replaceAll("\n", "\n\t"));
    }

    public String getHelp() {
        return HELP;
    }

    /**
     * Prints goodbye.
     */
    public String close() {
        sc.close();
        return EXIT;
    }

}
