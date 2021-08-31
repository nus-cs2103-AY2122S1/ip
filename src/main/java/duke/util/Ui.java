package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "\t_________________________________________________\n";
    private static final String GREET = "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private static final String EXIT = "Goodbye!~";

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

    /**
     * Formats the provided strings appropriately. The strings
     * provided will be formatted to be printable line by line.
     *
     * @param strings Strings to format to be line-by-line.
     * @return The formatted {@code String}s.
     */
    public String printLines(String... strings) {
        StringBuilder returnString = new StringBuilder();

        for (String string : strings) {
            returnString.append(String.format("%s\n", string));
        }

        return returnString.toString();
    }

    /**
     * Returns a {@code string} greeting.
     * @return greeting message.
     */
    public String getGreeting() {
        return GREET;
    }

    /**
     * Prints goodbye.
     */
    public String close() {
        sc.close();
        return EXIT;
    }

}
