import java.util.Scanner;

public class Duke {

    private static final String LINE_HORIZONTAL = "_______________________________";

    /***
     * Initializes the bot.
     */
    public static void main(String[] args) {

        // Prints initial message as prompt.
        System.out.printf("%s\nGreetings. This is Elsa.\nWhat can I do for you?\n%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);

        // Initializes scanner to take input from user.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Takes in input and performs actions accordingly.
        while(!(input = scanner.nextLine()).equalsIgnoreCase("bye")) {
            System.out.printf("%s\n%s\n%s\n", LINE_HORIZONTAL, input, LINE_HORIZONTAL);
        }

        // Bids farewell to user upon "bye" input.
        System.out.printf("%s\nGoodbye. Hope to see you again!\n", LINE_HORIZONTAL);

    }
}
