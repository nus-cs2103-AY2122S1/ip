import java.util.Scanner;

public class Duke {
    private final String BORDER = "\t_________________________________________________\n";
    private final String MESSAGE_GREET =  "Hi, my name is Nee ´･ᴗ･`. How can I help you?";
    private final String MESSAGE_EXIT = "Goodbye!~";

    /**
     * Prints user input sandwiched between two horizontal lines.
     *
     * @param input
     */
    public void print(String input) {
        String format = BORDER + "\t%s\n" + BORDER;
        System.out.printf(format, input.replaceAll("\n", "\n\t"));
    }

    public void run() {
        // Greet user
        print(MESSAGE_GREET);

        // User input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            print(input);
            input = sc.nextLine();
        }
        sc.close();

        // Goodbye message
        print(MESSAGE_EXIT);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
